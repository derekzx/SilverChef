package com.example.chinz_000.silverchef;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by crystal on 5/2/17.
 */

public class CurrentUser {
    public static int userid;
    public static String name;
    public static boolean old;
    public static int tel;

    private static ArrayList<Request> upcoming=new ArrayList<Request>();
    private static ArrayList<Request> pending = new ArrayList<Request>();

    public static void addUpcoming(Request newMeeting){
        upcoming.add(newMeeting);
    }

    public static void addPending(Request request){
        pending.add(request);
    }


    public static void removeUpcoming (Request request){
        for ( int i=0; i<upcoming.size(); i++){
            if (upcoming.get(i).getRequestid()==request.getRequestid()){
                upcoming.remove(i);
                break;
            }

        }

    }

    public static void removePending (Request request){
        for ( int i=0; i<pending.size(); i++){
            if (pending.get(i).getRequestid()==request.getRequestid()){
                pending.remove(i);
                break;
            }

        }

    }

    public static int getPendingReqIndex(int requestID){
        for(int i=0; i<pending.size();i++){
            if(pending.get(i).getRequestid()==requestID) {
                return i;
            }
        }
        return -1;
    }

    public static int getUpcomingReqIndex(int requestID){
        for(int i=0; i<pending.size();i++){
            if(pending.get(i).getRequestid()==requestID) {
                return i;
            }
        }
        return -1;
    }

    public static void removePending (int requestID){
        int index = getPendingReqIndex(requestID);
        pending.remove(index);
    }

    public static void removeUpcoming (int requestID){
        int index =getUpcomingReqIndex(requestID);
        upcoming.remove(index);
    }

    public static ArrayList<Request> getPending(){
        return pending;
    }

    public static ArrayList<Request> getUpcoming(){
        return upcoming;
    }



}
