package com.itus.u_money.mvp.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.itus.u_money.R;

import java.util.ArrayList;

public class TransactionFragment extends Fragment {
   private static TransactionFragment mInstance = null;
   private PieChart chart;

   public static TransactionFragment getInstance() {
      if (mInstance == null)
         mInstance = new TransactionFragment();
      return mInstance;
   }
   public TransactionFragment() {
   }
   @Nullable
   @Override
   public View onCreateView(
           @NonNull LayoutInflater inflater,
           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_transaction, container, false);
   }
   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      initChart();
      setData(5, 10);
   }

   void initChart() {
      chart = getActivity().findViewById(R.id.chart);
      chart.setUsePercentValues(true);
      chart.getDescription()
           .setEnabled(false);
      chart.setExtraOffsets(5, 10, 5, 5);

      chart.setDragDecelerationFrictionCoef(0.95f);
      chart.setDrawHoleEnabled(false);
      chart.setHoleColor(Color.WHITE);

      chart.setTransparentCircleColor(Color.WHITE);
      chart.setTransparentCircleAlpha(110);

      chart.setRotationAngle(0);
      // enable rotation of the chart by touch
      chart.setRotationEnabled(true);
      chart.setHighlightPerTapEnabled(true);
      chart.animateY(1400, Easing.EaseInOutQuad);
      // chart.spin(2000, 0, 360);

      chart.getLegend()
           .setEnabled(false);

      // entry label styling
      chart.setEntryLabelColor(Color.WHITE);
      chart.setEntryLabelTextSize(12f);
   }
   private void setData(int count, float range) {
      ArrayList<PieEntry> entries = new ArrayList<>();

      // NOTE: The order of the entries when being added to the entries array determines their position around the center of
      // the chart.
      for (int i = 0; i < count; i++) {
         entries.add(new PieEntry((float) ((Math.random() * range) + range / 5), "Test", 8));
      }

      PieDataSet dataSet = new PieDataSet(entries, "Election Results");

      dataSet.setDrawIcons(false);

      dataSet.setSliceSpace(3f);
      dataSet.setIconsOffset(new MPPointF(0, 40));
      dataSet.setSelectionShift(5f);

      // add a lot of colors

      ArrayList<Integer> colors = new ArrayList<>();

      for (int c : ColorTemplate.VORDIPLOM_COLORS)
         colors.add(c);

      for (int c : ColorTemplate.JOYFUL_COLORS)
         colors.add(c);

      for (int c : ColorTemplate.COLORFUL_COLORS)
         colors.add(c);

      for (int c : ColorTemplate.LIBERTY_COLORS)
         colors.add(c);
      chart.setDrawCenterText(false);

      for (int c : ColorTemplate.PASTEL_COLORS)
         colors.add(c);

      colors.add(ColorTemplate.getHoloBlue());

      dataSet.setColors(colors);
      //dataSet.setSelectionShift(0f);

      PieData data = new PieData(dataSet);
      data.setValueFormatter(new PercentFormatter(chart));
      data.setValueTextSize(11f);
      data.setValueTextColor(Color.WHITE);
      chart.setData(data);

      // undo all highlights
      chart.highlightValues(null);
      chart.invalidate();
   }

}
