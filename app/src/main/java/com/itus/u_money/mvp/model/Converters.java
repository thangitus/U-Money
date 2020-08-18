package com.itus.u_money.mvp.model;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
