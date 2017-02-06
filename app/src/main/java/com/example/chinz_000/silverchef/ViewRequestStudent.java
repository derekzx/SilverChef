package com.example.chinz_000.silverchef;

import android.app.ListActivity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crystal on 5/2/17.
 */
public class ViewRequestStudent extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);

        ListView yourListView = getListView();


        //get list of Upcoming
        ArrayList<Request> requests = CurrentUser.getUpcoming();

        if(requests!=null) {
            CustomAdapter adapter = new CustomAdapter(this, requests);
            setListAdapter(adapter);
        }

    }

}
