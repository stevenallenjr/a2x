package com.advisor2x.a2x;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://tender-babbage-b16bf0.netlify.app/webcasts.html").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Elements rows = doc.select("tr");

        String eventData = "";
        for(Element row :rows)
        {
            Elements columns = row.select("td");
            
            for (Element column:columns)
            {
                eventData += column.text() + "|";
            }
            String[] temp = eventData.split("\\|");
        }
    }
}
