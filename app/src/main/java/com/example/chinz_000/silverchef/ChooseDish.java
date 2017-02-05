package com.example.chinz_000.silverchef;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

public class ChooseDish extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_dish);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ImageButton button1 = (ImageButton) findViewById(R.id.imageButton1);
        final ImageButton button2 = (ImageButton) findViewById(R.id.imageButton2);
        final ImageButton button3 = (ImageButton) findViewById(R.id.imageButton3);
        final ImageButton button4 = (ImageButton) findViewById(R.id.imageButton4);
        final ImageButton button5 = (ImageButton) findViewById(R.id.imageButton5);
        final ImageButton button6 = (ImageButton) findViewById(R.id.imageButton6);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                View view2 = LayoutInflater.from(ChooseDish.this).inflate(R.layout.confirm_alert, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ChooseDish.this)
                        .setMessage("Confirm Student is "+ getString(R.string.student1))
                        .setView(view2)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // change User  = new ElderlyChoice(1);
                            }
                        })
                        .setNegativeButton ("Cancel", null)
                        .setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_dish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
