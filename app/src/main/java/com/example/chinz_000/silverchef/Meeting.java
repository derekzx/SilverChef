package com.example.chinz_000.silverchef;

/**
 * Created by crystal on 5/2/17.
 */
public class Meeting {
    private Request request;
    private boolean passed;


    public Meeting(Request request){
        this.request=request;
        this.passed=false;
    }

    public Integer getMentor(){
        return request.getMentorid();
    }

    public Integer getMentee(){
        return request.getMenteeid();
    }

    public void setPassed(){
        this.passed=true;
    }



}
