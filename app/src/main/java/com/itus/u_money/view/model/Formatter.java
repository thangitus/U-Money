package com.itus.u_money.view.model;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;

public class Formatter extends ValueFormatter {

   private final DecimalFormat mFormat;

   public Formatter() {
      mFormat = new DecimalFormat("######.0");
   }

   @Override
   public String getFormattedValue(float value) {
      return mFormat.format(value);
   }
}
