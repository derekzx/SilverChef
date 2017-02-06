package com.example.chinz_000.silverchef;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TeacherMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        new CountDownTimer(30000, 1000) {
            //find a way to get the data from appointment
            public void onTick(long millisUntilFinished) {
                TextView mTextField = (TextView) findViewById(R.id.mTextField);
                mTextField.setText("Time remaining to next chat: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                TextView mTextField = (TextView) findViewById(R.id.mTextField);
                mTextField.setText("done!");
            }
        }.start();

        final Button adviseStudent = (Button) findViewById(R.id.adviseStudent);
        adviseStudent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(TeacherMain.this, ChooseDish.class));
            }
        });
        final Button findChat = (Button) findViewById(R.id.videoButton);
        findChat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(TeacherMain.this, VideoActivity.class));
            }
        });
    }
}
