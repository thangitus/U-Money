package com.itus.u_money.view.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public static Date getFirstDayOfThisWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        return cal.getTime();
    }

    public static Date getLastDayOfThisWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        return cal.getTime();
    }

    public static Date getFirstDayOfThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getLastDayOfThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getFirstDayOfThisYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));
        return cal.getTime();
    }

    public static Date getLastDayOfThisYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
        return cal.getTime();
    }

    public static String getStringFirstDayOfThisWeek() {
        return format.format(getFirstDayOfThisWeek());
    }

    public static String getStringLastDayOfThisWeek() {
        return format.format(getLastDayOfThisWeek());
    }

    public static String getStringFirstDayOfThisMonth() {
        return format.format(getFirstDayOfThisMonth());
    }

    public static String getStringLastDayOfThisMonth() {
        return format.format(getLastDayOfThisMonth());
    }

    public static String getStringFirstDayOfThisYear() {
        return format.format(getFirstDayOfThisYear());
    }

    public static String getStringLastDayOfThisYear() {
        return format.format(getLastDayOfThisYear());
    }
}
