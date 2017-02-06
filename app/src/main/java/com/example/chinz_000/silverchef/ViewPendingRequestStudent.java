package com.example.chinz_000.silverchef;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by crystal on 6/2/17.
 */

public class ViewPendingRequestStudent extends ListActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pending);

        ListView yourListView = getListView();

        //get list of pending
        ArrayList<Request> requests = CurrentUser.getPending();

        if(requests!=null){
            CustomAdaptorPending adapter = new CustomAdaptorPending(this, requests);
            setListAdapter(adapter);
        }


    }
}
