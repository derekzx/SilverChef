/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.chinz_000.silverchef;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.example.chinz_000.silverchef.QuickstartPreferences.ENDPOINT;
import static com.example.chinz_000.silverchef.QuickstartPreferences.IDENTITY;
import static com.example.chinz_000.silverchef.QuickstartPreferences.REGISTRATION_COMPLETE;
import static com.example.chinz_000.silverchef.QuickstartPreferences.SENT_TOKEN_TO_SERVER;
import static com.example.chinz_000.silverchef.QuickstartPreferences.TOKEN;


public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private BindingResource bindingResource;
    private static final String schema = "https://";
    private static final String host = "8d337f08.ngrok.io"; //Do NOT include http://
    private static final int port = 4567;

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    public void onCreate(){
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(schema + "://" + host + ":" + port).addConverterFactory(JacksonConverterFactory.create())
                .build();

        bindingResource = retrofit.create(BindingResource.class);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        //Initialize registration parameters: identity, endpoint and token
        //And track if any of them changed otherwise we will not create a new Binding
        boolean identityChanged = false;
        boolean endpointChanged = false;
        boolean tokenChanged = false;

        String identity = sharedPreferences.getString(IDENTITY, null);
        String newIdentity = intent.getStringExtra(IDENTITY);

        boolean sentToken = sharedPreferences.getBoolean(SENT_TOKEN_TO_SERVER, false);

        //Only update identity if old and new are not equal
        if (newIdentity != null && !newIdentity.equals(identity)) {
            Log.d(TAG, "Old Identity: " + identity);
            identity = newIdentity;
            identityChanged = true;
            sharedPreferences.edit().putString(IDENTITY, identity).apply();
        }


        Log.i(TAG,"Identity: " + identity);

        String endpoint = sharedPreferences.getString(ENDPOINT, null);
        String token = sharedPreferences.getString(TOKEN, null);


        try {

            // [START get_token]
            String newToken = FirebaseInstanceId.getInstance().getToken();
            // [END get_token]

            //Note if token has changed
            tokenChanged = !newToken.equals(token);
            if (tokenChanged){
                Log.d(TAG, "Old FCM Registration Token: " + token);
                token = newToken;
                sharedPreferences.edit().putString(TOKEN, token).apply();
            }
            Log.i(TAG, "FCM Registration Token: " + newToken);

            //If there is no previous endpoint stored or there is a new Identity then create a new endpoint
            //This allows us to maintain stability of Endpoint even if instanceID changes without the identity changing
            if (endpoint == null || identityChanged){
                String newEndpoint = identity + "@" + FirebaseInstanceId.getInstance().getId();
                Log.d(TAG, "Old Endpoint: " + endpoint);
                endpoint = newEndpoint;
                sharedPreferences.edit().putString(ENDPOINT, endpoint).apply();
            }
            Log.i(TAG, "Endpoint: " + endpoint);


            if (!sentToken || identityChanged || endpointChanged || tokenChanged) {
                Log.i(TAG, "Some binding attributes have changed or binding creation have failed last time, creating new Binding");
                sendRegistrationToServer(identity, endpoint, token);
            }

            // You should store a boolean that indicates whether the generated token has been
            // sent to your server. If the boolean is false, send the token to your server,
            // otherwise your server should have already received the token.
            sharedPreferences.edit().putBoolean(SENT_TOKEN_TO_SERVER, true).apply();
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);
            // If an exception happens while fetching the new token or updating our registration data
            // on Twilio server, this ensures that we'll attempt the update at a later time.
            sharedPreferences.edit().putBoolean(SENT_TOKEN_TO_SERVER, false).apply();
        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    /**
     * Persist registration to Twilio via your application server.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String identity, String endpoint, String token) throws IOException {
        Call<Void> call = bindingResource.createBinding(identity, endpoint, token, "gcm");
        Response<Void> response = call.execute();
        if (!response.isSuccess()){
            throw new RuntimeException("Failed to send token to server. \n" + response.errorBody().string());
        }
    }

}
