package com.Calendar;

import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by BHARATH on 2/26/2016.
 */
public class Event{

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String eventName;
    private String eventDate;
    private String eventDescription;
    private String username;
    private int monthWeight;
    private int dateWeight;
    private String monthWeightS;
    private String dateWeightS;

    private int yearWeight;
    private String yearWeightS;

    Event(String name, String date, String desc, String uname, Integer id) {
        this.eventName = name;
        this.eventDate = date;
        this.eventDescription = desc;
        this.username = uname;
        this.id = id;
    }

    //============== Getters ====================================
    public String getEventDate() {
        return this.eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return eventDescription;
    }
    public String getUsername() {
        return username;
    }
    public int getMonthWeight() {return monthWeight;}
    public int getDateWeight() {return dateWeight;}
    public String getMonthWeightS() {return monthWeightS;}
    public String getDateWeightS() {return dateWeightS;}
    public int getYearWeight() {return yearWeight;}
    public String getYearWeightS() {return yearWeightS;}

    //============ Setters ==========================================
    public void setEventName(String name) {
        this.eventName = name;
    }

    public void setEventDate(String date) {
        this.eventDate = date;
    }

    public void setDescription(String body) {
        this.eventDescription = body;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setMonthWeight(int m){
        this.monthWeight = m;
    }
    public void setDateWeight(int d){
        this.dateWeight = d;
    }
    public void setMonthWeightS(String m){
        this.monthWeightS = m;
    }
    public void setDateWeightS(String d){
        this.dateWeightS = d;
    }

    public void setYearWeight(int y){
        this.yearWeight = y;
    }
    public void setYearWeightS(String y){
        this.yearWeightS = y;
    }
}
