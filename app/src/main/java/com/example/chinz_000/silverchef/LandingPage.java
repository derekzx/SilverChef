package com.example.chinz_000.silverchef;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LandingPage extends AppCompatActivity {
    public User user = new User();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets custom fonts
        TextView tx = (TextView)findViewById(R.id.question);
        Typeface Bold_OpenSans = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Bold.ttf");
        Typeface Regular_OpenSans = Typeface.createFromAsset(getAssets(),  "fonts/OpenSans-Regular.ttf");
        tx.setTypeface(Bold_OpenSans);

        final TextView mentorText = (TextView) findViewById(R.id.mentorText);
        mentorText.setTypeface(Bold_OpenSans);
        final TextView studentText = (TextView) findViewById(R.id.studentText);
        studentText.setTypeface(Bold_OpenSans);



        final ImageButton teacherButton = (ImageButton) findViewById(R.id.teacherButton);
        teacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view2 = LayoutInflater.from(LandingPage.this).inflate(R.layout.activity_sign_up, null);
                final EditText username = (EditText) view2.findViewById(R.id.username);
                final EditText handphone = (EditText) view2.findViewById(R.id.telephone);
                AlertDialog.Builder builder = new AlertDialog.Builder(LandingPage.this)
                        .setMessage("Sign Up")
                        .setView(view2)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                user.setUser(true);
                                user.setName(username.getText().toString());
                                CurrentUser.name=username.getText().toString();
                                int tel = Integer.parseInt(handphone.getText().toString());
                                user.setTel(tel);
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("Users/"+String.valueOf(tel));
                                myRef.setValue(user);
                                startActivity(new Intent(LandingPage.this, TeacherMain.class));
                            }
                        })
                        .setNegativeButton ("Cancel", null)
                        .setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();
            }

        });
        final ImageButton studentButton = (ImageButton) findViewById(R.id.studentButton);
        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {View view2 = LayoutInflater.from(LandingPage.this).inflate(R.layout.activity_sign_up, null);
                final EditText username = (EditText) view2.findViewById(R.id.username);
                final EditText handphone = (EditText) view2.findViewById(R.id.telephone);
                AlertDialog.Builder builder = new AlertDialog.Builder(LandingPage.this)
                        .setMessage("Sign Up")
                        .setView(view2)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                user.setUser(false);
                                user.setName(username.getText().toString());
                                CurrentUser.name=username.getText().toString();
                                int tel = Integer.parseInt(handphone.getText().toString());
                                user.setTel(tel);
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("Users/"+String.valueOf(tel));
                                myRef.setValue(user);
                                startActivity(new Intent(LandingPage.this, DashboardStudent.class));
                            }
                        })
                        .setNegativeButton ("Cancel", null)
                        .setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();


            }
        });
    }

}