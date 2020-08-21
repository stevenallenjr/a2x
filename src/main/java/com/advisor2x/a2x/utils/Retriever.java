package com.advisor2x.a2x.utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.advisor2x.a2x.data.Event;

public class Retriever {
        
    private static final String EVENTS_URL = "https://tender-babbage-b16bf0.netlify.app/webcasts.html";
    
    public static List<Event> getEvents() {
        Document doc = null;
        try {
            doc = Jsoup.connect(EVENTS_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        
        List<Event> retVal = new ArrayList<Event>();
        
        String eventData = "";
        
        Element table = doc.select("table").get(0);
        Elements tbody = table.select("tbody");
        Elements rows = tbody.select("tr");
        
        String[] temp = null;
        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            
            for(Element column:cols) {
                eventData += column.text() + "|";
            }
        }
        temp = eventData.split("\\|");
        
        int numEvents = temp.length / 5;
        for(int i=0;i<numEvents * 5;i+=5) {
            retVal.add(new Event(temp[i], temp[i+1], temp[i+2], temp[i+3], temp[i+4]));
        }
        
        return retVal;
    }
    
    public static Event getNextEvent(List<Event> events, Date today) {
        for(Event e:events) {
            try {
                if(DateUtils.compare(today, DateUtils.toDate(e.getDate())) == 0) {
                    return e;
                }
                else if(DateUtils.compare(today, DateUtils.toDate(e.getDate())) == 1) {
                    continue;
                }
                return e;
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
