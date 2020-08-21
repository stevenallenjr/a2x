package com.advisor2x.a2x.test;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.advisor2x.a2x.data.Event;
import com.advisor2x.a2x.utils.DateUtils;
import com.advisor2x.a2x.utils.Retriever;

public class Test {
    public static void main(String[] args) {
        Date today = new Date();
        Event e = Retriever.getNextEvent(Retriever.getEvents(), today);
        System.out.println(e);
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
