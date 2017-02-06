package com.example.chinz_000.silverchef;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*import com.twilio.twiliostarterapp.R; */

import static com.example.chinz_000.silverchef.QuickstartPreferences.IDENTITY;
import static com.example.chinz_000.silverchef.QuickstartPreferences.SENT_TOKEN_TO_SERVER;


public class
NotifyActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    TextView _resultText;
    EditText _identityText;
    Button _createBindingButton;
    private WakefulBroadcastReceiver mRegistrationBroadcastReceiver;
    ProgressDialog _progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRegistrationBroadcastReceiver = new WakefulBroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                boolean sentToken = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(SENT_TOKEN_TO_SERVER,false);
                if (sentToken) {
                    _resultText.setText("Binding successfully created");
                    _resultText.setVisibility(View.VISIBLE);
                } else {
                    _resultText.setText("Could not create Binding. Consult logs about the error.");
                    _resultText.setVisibility(View.VISIBLE);
                }

                if (_progressDialog != null){
                    _progressDialog.hide();
                }

                _createBindingButton.setEnabled(true);

            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
        setContentView(R.layout.activity_notify);
        _identityText = (EditText) findViewById(R.id.input_identity);
        _identityText.setText(PreferenceManager.getDefaultSharedPreferences(this).getString(IDENTITY, null));
        _createBindingButton = (Button) findViewById(R.id.btn_create_binding);
        _resultText = (TextView) findViewById(R.id.output_result);
        _resultText.setVisibility(View.INVISIBLE);
        _createBindingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                _resultText.setVisibility(View.INVISIBLE);
                _createBindingButton.setEnabled(false);
                createBinding();
            }
        });
    }


    public void createBinding() {
        Log.d(TAG, getString(R.string.CreatingBindingText));

        _progressDialog = new ProgressDialog(NotifyActivity.this,
                R.style.AppTheme_Dark_Dialog);
        _progressDialog.setIndeterminate(true);
        _progressDialog.setMessage(getString(R.string.CreatingBindingText));
        _progressDialog.show();

        String identity = _identityText.getText().toString();
        
        // Start IntentService to register this application with GCM.
        Intent intent = new Intent(this, RegistrationIntentService.class);
        intent.putExtra(IDENTITY, identity);
        startService(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this.getApplicationContext()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
    }
}
