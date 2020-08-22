package com.itus.u_money.mvp.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.itus.u_money.R;
import com.itus.u_money.mvp.view.model.DataBarChart;
import com.itus.u_money.mvp.view.model.Formatter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ReportFragment extends Fragment {
   private static ReportFragment mInstance = null;
   private BarChart chart;

   public static ReportFragment getInstance() {
      if (mInstance == null)
         mInstance = new ReportFragment();
      return mInstance;
   }
   public ReportFragment() {
   }
   @Nullable
   @Override
   public View onCreateView(
           @NonNull LayoutInflater inflater,
           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_report, container, false);
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      initChart();
   }
   private void initChart() {
      chart = getActivity().findViewById(R.id.bar_chart);
      chart.setExtraTopOffset(-30f);
      chart.setExtraBottomOffset(10f);

      chart.setDrawBarShadow(false);
      chart.setDrawValueAboveBar(true);

      chart.getDescription()
           .setEnabled(false);

      // scaling can now only be done on x- and y-axis separately
      chart.setPinchZoom(true);

      chart.setDrawGridBackground(false);

      XAxis xAxis = chart.getXAxis();
      xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
      xAxis.setDrawGridLines(false);
      xAxis.setDrawAxisLine(false);
      xAxis.setTextColor(Color.LTGRAY);
      xAxis.setTextSize(13f);
      xAxis.setLabelCount(5);
      xAxis.setCenterAxisLabels(true);
      xAxis.setGranularity(1f);

      YAxis left = chart.getAxisLeft();
      left.setDrawLabels(false);
      left.setSpaceTop(25f);
      left.setSpaceBottom(25f);
      left.setDrawAxisLine(false);
      left.setDrawGridLines(false);
      left.setDrawZeroLine(true); // draw a zero line
      left.setZeroLineColor(Color.GRAY);
      left.setZeroLineWidth(0.7f);
      chart.getAxisRight()
           .setEnabled(false);
      chart.getLegend()
           .setEnabled(false);

      // THIS IS THE ORIGINAL DATA YOU WANT TO PLOT
      final List<DataBarChart> data = new ArrayList<>();
      data.add(new DataBarChart(0f, -224.1f, "12-29"));
      data.add(new DataBarChart(1f, 238.5f, "12-30"));
      data.add(new DataBarChart(2f, 1280.1f, "12-31"));
      data.add(new DataBarChart(3f, -442.3f, "01-01"));
      data.add(new DataBarChart(4f, -2280.1f, "01-02"));

      xAxis.setValueFormatter(new ValueFormatter() {
         @Override
         public String getFormattedValue(float value) {
            return data.get(Math.min(Math.max((int) value, 0), data.size() - 1)).xAxisValue;
         }
      });

      setData(data);
   }
   private void setData(List<DataBarChart> dataList) {

      ArrayList<BarEntry> values = new ArrayList<>();
      List<Integer> colors = new ArrayList<>();

      int green = Color.rgb(110, 190, 102);
      int red = Color.rgb(211, 74, 88);

      for (int i = 0; i < dataList.size(); i++) {

         DataBarChart d = dataList.get(i);
         BarEntry entry = new BarEntry(d.xValue, d.yValue);
         values.add(entry);

         // specific colors
         if (d.yValue >= 0)
            colors.add(red);
         else
            colors.add(green);
      }

      BarDataSet set;

      if (chart.getData() != null && chart.getData()
                                          .getDataSetCount() > 0) {
         set = (BarDataSet) chart.getData()
                                 .getDataSetByIndex(0);
         set.setValues(values);
         chart.getData()
              .notifyDataChanged();
         chart.notifyDataSetChanged();
      } else {
         set = new BarDataSet(values, "Values");
         set.setColors(colors);
         set.setValueTextColors(colors);

         BarData data = new BarData(set);
         data.setValueTextSize(13f);
         data.setValueFormatter(new Formatter());
         data.setBarWidth(0.8f);

         chart.setData(data);
         chart.invalidate();
      }
   }

}
