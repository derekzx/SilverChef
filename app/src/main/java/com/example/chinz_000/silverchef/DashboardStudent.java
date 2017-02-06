package com.example.chinz_000.silverchef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by crystal on 5/2/17.
 */
public class DashboardStudent extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_student);

        final Button addBtn = (Button) findViewById(R.id.addRequestBtn);
        final Button upcomingBtn = (Button) findViewById(R.id.upcomingReqBtn);
        final Button pendingBtn = (Button) findViewById(R.id.pendingReqBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DashboardStudent.this, StartUp.class);
                startActivity(intent);

            }
        });

        upcomingBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DashboardStudent.this, ViewRequestStudent.class);
                startActivity(intent);

            }
        });

        pendingBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DashboardStudent.this, ViewPendingRequestStudent.class);
                startActivity(intent);

            }
        });
    }




}
