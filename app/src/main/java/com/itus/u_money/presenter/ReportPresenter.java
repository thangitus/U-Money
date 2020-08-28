package com.itus.u_money.presenter;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.itus.u_money.App;
import com.itus.u_money.contract.ReportContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.model.dao.TransactionDAO;
import com.itus.u_money.model.dao.TransactionTypeDAO;
import com.itus.u_money.view.model.DataBarChart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReportPresenter extends BaseCardPresenter implements ReportContract.Presenter, Observer<List<DataBarChart>> {
   private final ReportContract.View view;
   private MutableLiveData<List<DataBarChart>> dataBartChart;
   private long totalIn, totalOut, totalLoan, totalBorrow;
   public ReportPresenter(ReportContract.View view) {
      super(view);
      this.view = view;
      dataBartChart = new MutableLiveData<>();
      dataBartChart.observeForever(this);
   }
   @Override
   public void onMoveCurDateClick() {
      super.onMoveCurDateClick();
      getData();
   }
   @Override
   public void onNextClick() {
      super.onNextClick();
      getData();
   }
   @Override
   public void onPrevClick() {
      super.onPrevClick();
      getData();
   }
   @Override
   public void onDownClick() {
      super.onDownClick();
      getData();
   }
   @Override
   public void onUpClick() {
      super.onUpClick();
      getData();
   }
   @Override
   public void getData() {
      Context context = App.getContext();
      TransactionDAO transactionDAO = AppDatabase.getDatabase(context)
                                                 .transactionDAO();
      TransactionTypeDAO transactionTypeDAO = AppDatabase.getDatabase(context)
                                                         .transactionTypeDAO();

      switch (curReportBy) {
         case Day:
            getDataByDate(transactionDAO, transactionTypeDAO);
            break;
         case Month:
            getDataByMonth(transactionDAO, transactionTypeDAO);
            break;
         case Year:
            getDataByYear(transactionDAO, transactionTypeDAO);
      }
   }
   private void getDataByYear(TransactionDAO transactionDAO, TransactionTypeDAO transactionTypeDAO) {
      AppDatabase.executorService.execute(() -> {
         List<DataBarChart> dataBarChartList = new ArrayList<>();
         totalLoan = totalBorrow = totalOut = totalIn = 0;
         for (int i = 0; i < 12; i++) {
            long startTime = getTimeMonth(i);
            long endTime = getTimeMonth(i + 1);
            List<Transaction> transactions = transactionDAO.getByDate(startTime, endTime);
            long in, out;
            in = out = 0;
            for (Transaction transaction : transactions) {
               TransactionType transactionType = transactionTypeDAO.getById(transaction.transactionTypeId);
               if (transactionType.transactionGroupId == 1)
                  in += transaction.amount;
               else
                  out += transaction.amount;
               updateTotal(transaction, transactionType);
            }
            dataBarChartList.add(new DataBarChart(i, in, ""));
            dataBarChartList.add(new DataBarChart(i, -out, ""));
            totalOut += out;
            totalIn += in;
         }
         dataBartChart.postValue(dataBarChartList);
      });

   }
   private long getTimeMonth(int month) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.set(Calendar.MONTH, month);
      calendar.set(Calendar.DAY_OF_MONTH, 0);
      return calendar.getTime()
                     .getTime();
   }
   private void getDataByMonth(TransactionDAO transactionDAO, TransactionTypeDAO transactionTypeDAO) {
      AppDatabase.executorService.execute(() -> {
         List<DataBarChart> dataBarChartList = new ArrayList<>();
         totalLoan = totalBorrow = totalOut = totalIn = 0;
         for (int i = 0; i < 4; i++) {
            long startTime = getTimeWeek(i);
            long endTime = getTimeWeek(i + 1);
            List<Transaction> transactions = transactionDAO.getByDate(startTime, endTime);
            long in, out;
            in = out = 0;
            for (Transaction transaction : transactions) {
               TransactionType transactionType = transactionTypeDAO.getById(transaction.transactionTypeId);
               if (transactionType.transactionGroupId == 1)
                  in += transaction.amount;
               else
                  out += transaction.amount;
               updateTotal(transaction, transactionType);
            }
            dataBarChartList.add(new DataBarChart(i, in, ""));
            dataBarChartList.add(new DataBarChart(i, -out, ""));
         }
         dataBartChart.postValue(dataBarChartList);
      });

   }
   private void getDataByDate(TransactionDAO transactionDAO, TransactionTypeDAO transactionTypeDAO) {
      AppDatabase.executorService.execute(() -> {
         List<Transaction> transactions = transactionDAO.getByDate(getStartDay(), getEndDay());
         List<DataBarChart> dataBarChartList = new ArrayList<>();
         totalLoan = totalBorrow = totalOut = totalIn = 0;

         for (Transaction transaction : transactions) {
            TransactionType transactionType = transactionTypeDAO.getById(transaction.transactionTypeId);
            updateTotal(transaction, transactionType);
         }
         dataBarChartList.add(new DataBarChart(0f, totalIn, ""));
         dataBarChartList.add(new DataBarChart(0f, -totalOut, ""));
         dataBartChart.postValue(dataBarChartList);
      });
   }
   private void updateTotal(Transaction transaction, TransactionType transactionType) {
      if (transactionType.transactionGroupId == 1)
         totalIn += transaction.amount;
      else
         totalOut += transaction.amount;

      if (transactionType.name.equals("Đi vay"))
         totalLoan += transaction.amount;

      if (transactionType.name.equals("Trả nợ"))
         totalLoan -= transaction.amount;

      if (transactionType.name.equals("Cho vay"))
         totalBorrow += transaction.amount;

      if (transactionType.name.equals("Thu nợ"))
         totalBorrow -= transaction.amount;
   }
   @Override
   public void onChanged(List<DataBarChart> dataBarCharts) {
      view.updateChartData(dataBarCharts, animateChart);
      view.updateInOut(totalIn, totalOut);
      view.updateLoan(totalLoan, totalBorrow);
   }

   private long getTimeWeek(int week) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.set(Calendar.DAY_OF_MONTH, week * 7 + 1);
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      return calendar.getTime()
                     .getTime();
   }
}
