package com.advisor2x.a2x.data;

import com.advisor2x.a2x.utils.DateFormatter;

public class Event {
    private String date;
    private String time;
    private String speaker;
    private String desc;
    private String title;
    
    
    public Event(String date, String time, String speaker, String title, String desc) {
        this.date = date;
        this.time = time;
        this.speaker = speaker;
        this.desc = desc;
        this.title = title;
    }
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getSpeaker() {
        return speaker;
    }
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDateAndTime() {
        return date + time;
    }
    
    public String toString() {
        return "Join us on " + DateFormatter.getDateReadout(date) + " for the Excel 401K session, " + title + ", with "
                + speaker + ". " + desc;
    }
}
