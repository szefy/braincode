package com.example.root.myapplication.rest.utils;

import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by jbis on 2015-03-14.
 */
public class DateTimeUtils {
    public static final String LONG = "yyyy-MM-dd hh:mm:ss";
    public static final String SHORT = "hh:mm:ss";

    public static String getLong(Date date) {
        DateFormat df = new DateFormat();
        return df.format(LONG, date).toString();
    }

    public static String getShort(Date date) {
        DateFormat df = new DateFormat();
        return df.format(SHORT, date).toString();
    }
}
