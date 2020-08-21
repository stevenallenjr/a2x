package com.advisor2x.a2x.test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.advisor2x.a2x.data.Event;
import com.advisor2x.a2x.utils.DateFormatter;
import com.advisor2x.a2x.utils.DateUtils;
import com.advisor2x.a2x.utils.Retriever;

public class Test {
    public static void main(String[] args) {
        Date today = new Date();
        Date session = null;
        List<Event> events = Retriever.getEvents();
        Event nextEvent = null;
        try {
            nextEvent = Retriever.getNextEvent(events,today);
            if(nextEvent != null)
                session = DateUtils.toDate(nextEvent.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        String speechText;
        
        String amzDate = "2020-08-09";
        Date amz = null;
        Date other = null;
        try {
            amz = DateUtils.fromAmz(amzDate);
            other = DateUtils.toDate("08052020");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        int c = DateUtils.compare(amz, other);

        for(int i=0;i<events.size();i++) {
            System.out.println(events.get(i).getTitle());
        }
        
        System.out.println(amz);
        System.out.println(other);
        System.out.println(c);
        
        
        System.out.println(getEventOnDate(amz, events));
        
        
        
        if(nextEvent == null) {
            speechText = "There are no upcoming digital sessions.";
        }
        
        else {
            speechText = "Join us " + (DateUtils.compare(today, session) == 0 ? "today " : "on ")
                    + DateFormatter.getDateAndTimeReadout(nextEvent.getDate(), 
                    nextEvent.getTime()) + " for the Excel 401K session, " + nextEvent.getTitle() + ", with "
                    + nextEvent.getSpeaker() + ". " + nextEvent.getDesc();
        }
        
        System.out.println(speechText);
    }
    
    private static Event getEventOnDate(Date d, List<Event> events) {
        for(int i=0;i<events.size();i++) {
            
            int c = -999;
            try {
                c = DateUtils.compare(d, DateUtils.toDate(events.get(i).getDate()));
                System.out.println(d + " compared to " + DateUtils.toDate(events.get(i).getDate()) + " is " + c);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            if(c == 0) {
                return events.get(i);
            }
        }
        return null;
    }
}
