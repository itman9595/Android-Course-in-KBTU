package com.example.bauyrzhan.calculator;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class MessageInfo implements Comparable<MessageInfo>{

    String date;
    String title;
    String sender;
    String subject;
    String id;

    public MessageInfo(String d, String t, String i, String sn, String s){
        this.date = d;
        this.title = t;
        this.id = i;
        this.sender = sn;
        this.subject = s;
    }

    public String getDate(){
        return  this.date;
    }

    public String getTitle(){
        return  this.title;
    }

    public String getID() {
        return this.id;
    }

    public String getSender(){
        return this.sender;
    }

    public String getSubject() {
        return this.subject;
    }

    public int compareTo(MessageInfo mesInf) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try{
            return sdf.parse(this.getDate()).compareTo(sdf.parse(mesInf.getDate()));
        }catch (ParseException e){
            return Log.e("Parse", "Error");
        }
    }

}
