package com.itus.u_money.model;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Converters {
   @TypeConverter
   public static Date fromTimestamp(Long value) {
      return value == 0 ? null : new Date(value);
   }

   @TypeConverter
   public static long dateToTimestamp(Date date) {
      return date == null ? 0 : date.getTime();
   }

   @TypeConverter
   public static List<Integer> fromString(String list) {
      String[] tokens = list.split(",");
      List<Integer> integerList = new ArrayList<>();
      for (String token : tokens) {
         if (token.equals(""))
            continue;
         integerList.add(Integer.parseInt(token));
      }
      return integerList;
   }

   @TypeConverter
   public static String intListToString(List<Integer> list) {
      List<String> stringList = new ArrayList<>();
      for (Integer i : list) {
         stringList.add(i.toString());
      }
      return String.join(",", stringList);
   }
}
