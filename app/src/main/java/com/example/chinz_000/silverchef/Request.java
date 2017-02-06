package com.example.chinz_000.silverchef;

/**
 * Created by crystal on 5/2/17.
 */
public class Request {
    private String dish;

    private int hhmmp;
    private int ddmmyy;

    private boolean accepted;
    private int menteeid;
    private int mentorid;
    private String menteeName;
    private String mentorName = " ";
    private int requestid;
    private static int requestidCount=1;

    public Request(){}

//    public Request(String dish, int day, int month, int year, int hour, int min,
//                   int ampm){
//        this.dish = dish;
//        this.day = day;
//        this.month = month;
//        this.year = year;
//
//        this.hour = hour;
//        this.min = min;
//        this.ampm = ampm;
//
//        this.accepted=false;
//        this.requestid=++requestidCount;
//        //set user id
//    }

    public Request (String dish, int hhmmp, int ddmmyy, int menteeid, String menteeName){
        this.dish = dish;
        this.hhmmp = hhmmp;
        this.ddmmyy = ddmmyy;//HHmmAMDDMMYYYY
        this.menteeid = menteeid;
        this.menteeName = menteeName;
        this.requestid=++requestidCount;
    }

    public String getDish(){
        return dish;
    }

    public String getMenteeName(){
        return menteeName;
    }

    public String getMentorName(){return mentorName; }

    public void setMentorName(String name){
        this.mentorName = name;
    }

//    public int getDay(){
//        return timestamp[3];
//    }
//
//    public int getMonth(){
//        return timestamp[4];
//    }
//
//    public int getYear(){
//        return timestamp[5];
//    }
//
//    public int getHour(){
//        return timestamp[0];
//    }
//
//    public int getMin(){
//        return timestamp[1];
//    }
//
//    public int getAmpm(){
//        return timestamp[2];
//    }

    public int getMenteeid(){
        return menteeid;
    }

    public int getMentorid(){
        return mentorid;
    }

    public int getHhmmp(){//HHmmDDMMYY
        return hhmmp;
    }

    public int getDdmmyy(){ return ddmmyy; }

    public int getRequestid(){
        return requestid;
    }

    public boolean getAccepted(){
        return accepted;
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
