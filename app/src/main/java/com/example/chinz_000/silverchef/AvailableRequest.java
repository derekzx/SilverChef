package com.example.chinz_000.silverchef;


import java.util.ArrayList;

/**
 * Created by crystal on 5/2/17.
 */
public class AvailableRequest {
    private static ArrayList<Request> unacceptedRequests;

    public static Request getRequest(int index){
        return unacceptedRequests.get(index);
    }

    public static int getNumReq(){
        return unacceptedRequests.size();
    }

    public static void addReq(Request request){
        unacceptedRequests.add(request);
    }

    public static int getReqIndex(int requestID){
        for(int i=0; i<unacceptedRequests.size();i++){
            if(unacceptedRequests.get(i).getRequestid()==requestID) {
                return i;
            }
        }
        return -1;
    }

    public static Request getReq(int requestID){
        int index = getReqIndex(requestID);
        if (index!=-1)
            return unacceptedRequests.get(index);
        else return null;
    }

    public static void removeReq (int requestID){
        int index =getReqIndex(requestID);
        unacceptedRequests.remove(index);
    }


    public static ArrayList<Request> getRequestList(){
        return unacceptedRequests;
    }

    public static void acceptRequest(int requestID, int mentorID){
        int index = getReqIndex(requestID);
        Request request = unacceptedRequests.get(index);
        request.setAccepted();
        request.setMentorid(mentorID);
        unacceptedRequests.remove(index);
        //remove from pending in user
        //create meeting instance in user

    }


}
