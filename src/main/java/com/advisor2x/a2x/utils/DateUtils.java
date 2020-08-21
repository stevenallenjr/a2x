package com.advisor2x.a2x.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    
    private static SimpleDateFormat dtFormatter = new SimpleDateFormat("MMddyyyyHHmm");
    private static SimpleDateFormat dFormatter = new SimpleDateFormat("MMddyyyy");
    private static SimpleDateFormat amzFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat timeFrom = new SimpleDateFormat("HHmm");
    private static SimpleDateFormat timeTo = new SimpleDateFormat("hh:mm aa");
    
    public static Date toDateAndTime(String arg) throws ParseException {
        return dtFormatter.parse(arg);
    }
    
    public static Date toDate(String arg) throws ParseException {
        return dFormatter.parse(arg);
    }
    
    public static Date fromAmz(String arg) throws ParseException {
        return amzFormatter.parse(arg);
    }
    
    public static int compare(Date arg1, Date arg2) {
        return arg1.compareTo(arg2);
    }
    
    public static boolean isBefore(Date arg1, Date arg2) {
        return arg1.before(arg2);
    }
    
    public static String convertTime(String time) throws ParseException {
        return timeTo.format(timeFrom.parse(time));
    }
}
