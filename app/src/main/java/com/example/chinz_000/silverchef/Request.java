package com.example.chinz_000.silverchef;

/**
 * Created by crystal on 5/2/17.
 */
public class Request {
    private String dish;
    private int day;
    private int month;
    private int year;

    private int hour;
    private int min;

    private int[] timestamp;
    private boolean ampm;

    private boolean accepted;
    private int menteeid;
    private int mentorid;
    private int requestid;
    private static int requestidCount=1;

    public Request(String dish, int day, int month, int year, int hour, int min,
                   boolean ampm){
        this.dish = dish;
        this.day = day;
        this.month = month;
        this.year = year;

        this.hour = hour;
        this.min = min;
        this.ampm = ampm;

        this.accepted=false;
        this.requestid=++requestidCount;
        //set user id
    }

    public Request (String dish, int[] timestamp, boolean ampm, int menteeid){
        this.dish = dish;
        this.timestamp = timestamp; //HHmmDDMMYYYY
        this.ampm = ampm;
        this.menteeid = menteeid;
        this.requestid=++requestidCount;
    }

    public String getDish(){
        return dish;
    }

    public int getDay(){
        return timestamp[2];
    }

    public int getMonth(){
        return timestamp[3];
    }

    public int getYear(){
        return timestamp[4];
    }

    public int getHour(){
        return timestamp[0];
    }

    public int getMin(){
        return timestamp[1];
    }

    public boolean getAmpm(){
        return ampm;
    }

    public int getMenteeid(){
        return menteeid;
    }

    public int getMentorid(){
        return mentorid;
    }

    public int[] getMeetingTimestamp(){//HHmmDDMMYY
        return timestamp;
    }

    public int getRequestid(){
        return requestid;
    }


    //who requested

    public void setAccepted(){
        this.accepted=true;
    }

    public void setMentorid(int id){
        this.mentorid=id;
    }



    public void acceptRequest(int mentorid){
        setAccepted();
        setMentorid(mentorid);
    }



    //who accept
    //

}
