package net.c0nan.agic.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeStampUtil {
    public static String getTimeStamp() {
        TimeZone tz = TimeZone.getTimeZone("AEST");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        return df.format(new Date());
    }
}
