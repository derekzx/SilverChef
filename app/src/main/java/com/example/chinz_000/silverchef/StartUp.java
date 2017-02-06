package com.example.chinz_000.silverchef;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        final TextView title = (TextView) findViewById(R.id.titletext);

        final Button button = (Button) findViewById(R.id.submitbutton);
        final EditText dishText = (EditText) findViewById(R.id.editText);

        final Spinner dateSpinner = (Spinner) findViewById(R.id.datespinner);
        final Spinner monthSpinner = (Spinner) findViewById(R.id.monthspinner);
        final Spinner yearSpinner = (Spinner) findViewById(R.id.yearspinner);

        final Spinner hourSpinner = (Spinner) findViewById(R.id.hourspinner);
        final Spinner minSpinner = (Spinner) findViewById(R.id.minspinner);
        final Spinner ampmSpinner = (Spinner) findViewById(R.id.ampmspinner);


        Integer[] daysofmonth = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        ArrayAdapter<Integer> dayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, daysofmonth);
        dateSpinner.setAdapter(dayAdapter);

        String[] monthsofyear = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, monthsofyear);
        monthSpinner.setAdapter(monthAdapter);

        Integer[] years = new Integer[]{2017, 2018};
        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, years);
        yearSpinner.setAdapter(yearAdapter);


        Integer[] hour = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        ArrayAdapter<Integer> hourAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, hour);
        hourSpinner.setAdapter(hourAdapter);

        Integer[] min = new Integer[]{00, 15, 30, 45, 60};
        ArrayAdapter<Integer> minAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, min);
        minSpinner.setAdapter(minAdapter);

        String[] ampm = new String[]{"am", "pm"};
        ArrayAdapter<String> ampmAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ampm);
        ampmSpinner.setAdapter(ampmAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //create new instance of request with variables
                Integer date = Integer.valueOf(dateSpinner.getSelectedItem().toString());
                String month = monthSpinner.getSelectedItem().toString();
                Integer year = Integer.valueOf(yearSpinner.getSelectedItem().toString());

                Integer hour = Integer.valueOf(hourSpinner.getSelectedItem().toString());
                Integer min = Integer.valueOf(minSpinner.getSelectedItem().toString());
                String ampm = ampmSpinner.getSelectedItem().toString();

                int monthInt=0;

                switch(month){
                    case "Jan":monthInt=1;
                        break;
                    case "Feb":monthInt=2;
                        break;
                    case "Mar":monthInt=3;
                        break;
                    case "Apr":monthInt=4;
                        break;
                    case "May":monthInt=5;
                        break;
                    case "Jun":monthInt=6;
                        break;
                    case "Jul":monthInt=7;
                        break;
                    case "Aug":monthInt=8;
                        break;
                    case "Sep":monthInt=9;
                        break;
                    case "Oct":monthInt=10;
                        break;
                    case "Nov":monthInt=11;
                        break;
                    case "Dec":monthInt=12;
                        break;
                }
                int ampmInt;
                if(ampm=="am"){
                    ampmInt = 1;
                }
                else ampmInt = 0;

                final String dish = dishText.getText().toString();
                final int hhmmp = (hour*1000)+(min*10)+ampmInt;
                final int ddmmyy = (date*10000)+(monthInt*100)+(year%100);
                final int menteeid = CurrentUser.userid;



                View view2 = LayoutInflater.from(StartUp.this).inflate(R.layout.confirm_alert, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(StartUp.this)
                        .setMessage("Confirm request submission?")
                        .setView(view2)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //create Request
                                Request request = new Request(dish,hhmmp,ddmmyy,menteeid,CurrentUser.name);
                                FirebaseDatabase requestDatabase = FirebaseDatabase.getInstance();
                                //put key
                                DatabaseReference myRef = requestDatabase.getReference("Request/"+String.valueOf(request.getRequestid()));
                                //put rest of data
                                myRef.setValue(request);
                                CurrentUser.addPending(request);
                                //add request to user's pending request
                                //add request to available requests
                                Intent intent = new Intent(StartUp.this, requestSuccessRedirection.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton ("Cancel", null)
                        .setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();


            }
        });


    }

    @Override
    public void onDestroy()
    {

        super.onDestroy();
    }
}


