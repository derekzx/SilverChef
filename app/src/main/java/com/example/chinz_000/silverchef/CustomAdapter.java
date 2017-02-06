package com.example.chinz_000.silverchef;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by crystal on 5/2/17.
 */

public class CustomAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<Request> requests;

    public CustomAdapter(Context context, ArrayList<Request> values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.requests = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView dishText = (TextView) rowView.findViewById(R.id.dishName);
        TextView nameText = (TextView) rowView.findViewById(R.id.userName);
        TextView timeText = (TextView) rowView.findViewById(R.id.timeMeeting);

        dishText.setText(requests.get(position).getDish());
        nameText.setText(requests.get(position).getMentorName());
        int date = requests.get(position).getDdmmyy();
        int time = requests.get(position).getHhmmp();
        String t = String.valueOf(date/10000);

        switch(date/100%100){
            case 1:t+=" January ";
                break;
            case 2:t+=" February ";
                break;
            case 3:t+=" March ";
                break;
            case 4: t+=" April ";
                break;
            case 5:
                t+=" May ";
                break;
            case 6:
                t+=" June ";
                break;
            case 7:
                t+=" July ";
                break;
            case 8:
                t+=" August ";
                break;
            case 9:
                t+=" September ";
                break;
            case 10:
                t+=" October ";
                break;
            case 11:
                t+=" November ";
                break;
            case 12:
                t+=" Decemeber ";
                break;
        }
        t+="20"+String.valueOf(date%100);
        t+=" "+ String.valueOf(time/1000)+":"+String.valueOf(time/10%100);
        if(time%10==1){
            t+="AM";
        } else t+="PM";
        timeText.setText(t);

        return rowView;
    }
}
