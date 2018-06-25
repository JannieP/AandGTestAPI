package net.c0nan.agic.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeStampUtil {
    public static String getTimeStamp() {
        TimeZone timeZone = TimeZone.getTimeZone("AEST");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(new Date());
    }
}
