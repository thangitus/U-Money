package com.itus.u_money.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.itus.u_money.R;
import com.itus.u_money.databinding.FragmentTransactionBinding;
import com.itus.u_money.contract.TransactionContract;
import com.itus.u_money.model.Icon;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.presenter.TransactionPresenter;
import com.itus.u_money.view.adapter.SummaryAdapter;
import com.itus.u_money.view.adapter.TransactionAdapter;
import com.itus.u_money.view.utils.SpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class TransactionFragment extends Fragment implements TransactionContract.View {
   private static TransactionFragment mInstance = null;
   private PieChart chart;
   FragmentTransactionBinding binding;
   private TransactionContract.Presenter presenter;
   private TransactionAdapter transactionAdapter;
   private SummaryAdapter summaryAdapter;
   public static TransactionFragment getInstance() {
      if (mInstance == null)
         mInstance = new TransactionFragment();
      return mInstance;
   }
   public TransactionFragment() {
      presenter = new TransactionPresenter(this);
   }

   @Nullable
   @Override
   public View onCreateView(
           @NonNull LayoutInflater inflater,
           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction, container, false);
      binding.setPresenter(presenter);
      return binding.getRoot();
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      initChart();
      initRecyclerView(binding.recyclerviewListTransaction);
      presenter.onViewCreated();
   }
   @Override
   public void onResume() {
      super.onResume();
      presenter.getListTransaction();
   }

   private void initRecyclerView(RecyclerView recyclerView) {
      transactionAdapter = new TransactionAdapter();
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
      recyclerView.setAdapter(transactionAdapter);
      recyclerView.addItemDecoration(new SpacingItemDecoration(12));
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
      // chart.spin(2000, 0, 360);

      chart.getLegend()
           .setEnabled(false);

      // entry label styling
      chart.setEntryLabelColor(Color.WHITE);
      chart.setEntryLabelTextSize(12f);

      summaryAdapter = new SummaryAdapter();
      binding.recyclerviewSummary.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
      binding.recyclerviewSummary.setAdapter(summaryAdapter);
   }

   @Override
   public void updateReportBy(String text, boolean increase) {
      binding.textReportBy.setText(text);
      Animation animation;
      if (increase)
         animation = AnimationUtils.loadAnimation(getContext(), R.anim.up_in);
      else
         animation = AnimationUtils.loadAnimation(getContext(), R.anim.down_in);
      animation.reset();
      binding.textReportBy.clearAnimation();
      binding.textReportBy.startAnimation(animation);
   }

   @Override
   public void updateDate(String text) {
      binding.tvDate.setText(text);
   }

   @Override
   public void upDateChart(boolean next) {
      Animation animationOut, animationIn;
      if (next) {
         animationOut = AnimationUtils.loadAnimation(getContext(), R.anim.right_out);
         animationIn = AnimationUtils.loadAnimation(getContext(), R.anim.left_in);
      } else {
         animationOut = AnimationUtils.loadAnimation(getContext(), R.anim.left_out);
         animationIn = AnimationUtils.loadAnimation(getContext(), R.anim.right_in);
      }
      animationOut.setAnimationListener(new Animation.AnimationListener() {
         @Override
         public void onAnimationStart(Animation animation) {

         }
         @Override
         public void onAnimationEnd(Animation animation) {
            chart.clearAnimation();
            chart.startAnimation(animationIn);
         }
         @Override
         public void onAnimationRepeat(Animation animation) {

         }
      });
      chart.startAnimation(animationOut);
   }
   @Override
   public void animateChart() {
      chart.animateY(500, Easing.EaseInOutQuad);
   }

   @Override
   public void showTransactionList(List<Transaction> transactionList, List<TransactionType> transactionTypes, List<Icon> icons) {
      transactionAdapter.setTransactionList(transactionList);
      transactionAdapter.setTransactionTypeList(transactionTypes);
      transactionAdapter.setIconList(icons);
      transactionAdapter.notifyDataSetChanged();
   }

   @Override
   public void updateChartData(List<TransactionType> transactionTypes, List<Icon> icons, boolean animate) {
      binding.recyclerviewListTransaction.removeAllViews();
      summaryAdapter.setData(transactionTypes);
      summaryAdapter.notifyDataSetChanged();

      ArrayList<PieEntry> entries = new ArrayList<>();
      for (int i = 0; i < transactionTypes.size(); i++) {
         TransactionType transactionType = transactionTypes.get(i);
         entries.add(new PieEntry(transactionType.total, transactionType.name));
      }

      PieDataSet dataSet = new PieDataSet(entries, "Election Results");
      dataSet.setDrawIcons(false);
      dataSet.setSliceSpace(3f);
      dataSet.setIconsOffset(new MPPointF(0, 40));
      dataSet.setSelectionShift(5f);
      ArrayList<Integer> colors = new ArrayList<>();
      for (Icon icon : icons)
         colors.add(Color.parseColor(icon.backgroundColor));

      dataSet.setColors(colors);
      PieData data = new PieData(dataSet);
      data.setValueFormatter(new PercentFormatter(chart));
      data.setValueTextSize(11f);
      data.setValueTextColor(Color.WHITE);
      chart.setData(data);
      if (animate)
         chart.animateY(500, Easing.EaseInOutQuad);
      chart.highlightValues(null);
      chart.invalidate();
   }
}
