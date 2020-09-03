package com.itus.u_money.presenter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.itus.u_money.App;
import com.itus.u_money.contract.DetailTransactionContract;
import com.itus.u_money.contract.TransactionContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Icon;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.model.dao.IconDAO;
import com.itus.u_money.model.dao.TransactionDAO;
import com.itus.u_money.model.dao.TransactionTypeDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionPresenter extends BaseCardPresenter implements TransactionContract.Presenter, Observer<List<TransactionType>> {
   private final TransactionContract.View view;
   private List<Transaction> transactionList;
   private List<Icon> iconList;
   private List<Icon> iconListForChart;
   private List<TransactionType> typeListForChart;
   private MutableLiveData<List<TransactionType>> listLiveData;
   public TransactionPresenter(TransactionContract.View view) {
      super(view);
      this.view = view;
      listLiveData = new MutableLiveData<>();
      listLiveData.observeForever(this);
      iconList = new ArrayList<>();
      typeListForChart = new ArrayList<>();
      iconListForChart = new ArrayList<>();
   }

   @Override
   public void onMoveCurDateClick() {
      super.onMoveCurDateClick();
      getListTransaction();
   }
   @Override
   public void onNextClick() {
      super.onNextClick();
      getListTransaction();
   }
   @Override
   public void onPrevClick() {
      super.onPrevClick();
      getListTransaction();
   }
   @Override
   public void onDownClick() {
      super.onDownClick();
      getListTransaction();
   }
   @Override
   public void onUpClick() {
      super.onUpClick();
      getListTransaction();
   }

   @Override
   public void getListTransaction() {
      TransactionDAO transactionDAO = AppDatabase.getDatabase(App.getContext())
                                                 .transactionDAO();
      TransactionTypeDAO transactionTypeDAO = AppDatabase.getDatabase(App.getContext())
                                                         .transactionTypeDAO();
      IconDAO iconDAO = AppDatabase.getDatabase(App.getContext())
                                   .iconDAO();
      Long startDate = getStartDay();
      Long endDate = getEndDay();
      AppDatabase.executorService.execute(() -> {
         transactionList = transactionDAO.getByDate(startDate, endDate);

         iconList.clear();
         typeListForChart.clear();
         iconListForChart.clear();
         List<TransactionType> typesTmp = new ArrayList<>();

         HashMap<Integer, TransactionType> typeHashMap = new HashMap<>();
         for (Transaction transaction : transactionList) {
            TransactionType transactionType = transactionTypeDAO.getById(transaction.transactionTypeId);
            Icon icon = iconDAO.getIconById(transactionType.iconId);
            typesTmp.add(transactionType);
            iconList.add(icon);

            TransactionType transactionTypeInMap = typeHashMap.get(transaction.transactionTypeId);
            if (transactionTypeInMap == null) {
               transactionTypeInMap = transactionType;
               transactionTypeInMap.total = transaction.amount;
            } else
               transactionTypeInMap.total += transaction.amount;

            typeHashMap.put(transaction.transactionTypeId, transactionTypeInMap);
         }

         for (Transaction transaction : transactionList) {
            TransactionType transactionType = typeHashMap.get(transaction.transactionTypeId);
            if (transactionType != null) {
               iconListForChart.add(iconDAO.getIconById(transactionType.iconId));
               typeListForChart.add(transactionType);
               typeHashMap.remove(transaction.transactionTypeId);
            }

         }
         listLiveData.postValue(typesTmp);
      });
   }

   @Override
   public void onChanged(List<TransactionType> transactionTypes) {
      view.showTransactionList(transactionList, transactionTypes, iconList);

      view.updateChartData(typeListForChart, iconListForChart, animateChart);
   }
}
