package com.itus.u_money.view.model;

public class DataBarChart {

   public String xAxisValue;
   public float yValue;
   public float xValue;

   public DataBarChart(float xValue, float yValue, String xAxisValue) {
      this.xAxisValue = xAxisValue;
      this.yValue = yValue;
      this.xValue = xValue;
   }
}