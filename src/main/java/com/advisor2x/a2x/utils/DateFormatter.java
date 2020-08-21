package com.advisor2x.a2x.utils;

import java.text.ParseException;
import java.util.HashMap;

public class DateFormatter {
    
    public static HashMap<Integer, String> months;
    static {
        months = new HashMap<Integer, String>();
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");
    }
    
    public static String getDateReadout(String date) {
        String month = months.get(Integer.parseInt(date.substring(0,2)));
        return month + " " + ordinal(Integer.parseInt(date.substring(2,4)));
    }
    
    public static String getAmzDateReadout(String date) {
        //2020-08-07
        String month = months.get(Integer.parseInt(date.substring(5,7)));
        return month + " " + ordinal(Integer.parseInt(date.substring(8,10)));
    }
    
    public static String getDateAndTimeReadout(String date, String time) {
        String p1 = getDateReadout(date);
        String p2;
        try {
            p2 = DateUtils.convertTime(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return p1 + " at " + p2;
    }
    
    public static String ordinal(int i) {
        String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
        case 11:
        case 12:
        case 13:
            return i + "th";
        default:
            return i + sufixes[i % 10];

        }
    }
}
