package com.example.chinz_000.silverchef;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by crystal on 5/2/17.
 */
public class requestSuccessRedirection extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_success);

        int DELAY = 1000;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(requestSuccessRedirection.this, DashboardStudent.class);
                startActivity(intent);
            }
        }, DELAY);

    }


}
