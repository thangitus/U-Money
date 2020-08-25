package com.itus.u_money.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.itus.u_money.App;
import com.itus.u_money.contract.TransactionContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Icon;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.model.dao.IconDAO;
import com.itus.u_money.model.dao.TransactionDAO;
import com.itus.u_money.model.dao.TransactionTypeDAO;

import java.util.ArrayList;
import java.util.List;

public class TransactionPresenter extends BaseCardPresenter implements TransactionContract.Presenter, Observer<List<TransactionType>> {
   private final TransactionContract.View view;
   private List<Transaction> transactionList;
   private List<Icon> iconList;
   private MutableLiveData<List<TransactionType>> listLiveData;
   public TransactionPresenter(TransactionContract.View view) {
      super(view);
      this.view = view;
      listLiveData = new MutableLiveData<>();
      listLiveData.observeForever(this);
   }

   @Override
   public void getData() {
      TransactionDAO transactionDAO = AppDatabase.getDatabase(App.getContext())
                                                 .transactionDAO();
      TransactionTypeDAO transactionTypeDAO = AppDatabase.getDatabase(App.getContext())
                                                         .transactionTypeDAO();
      IconDAO iconDAO = AppDatabase.getDatabase(App.getContext())
                                   .iconDAO();

      AppDatabase.executorService.execute(() -> {
         transactionList = transactionDAO.getAll();
         List<TransactionType> typesTmp = new ArrayList<>();
         List<Icon> iconListTmp = new ArrayList<>();

         for (Transaction transaction : transactionList) {
            TransactionType transactionType = transactionTypeDAO.getById(transaction.transactionTypeId);
            Icon icon = iconDAO.getIconById(transactionType.iconId);
            typesTmp.add(transactionType);
            iconListTmp.add(icon);
         }
         iconList = iconListTmp;
         listLiveData.postValue(typesTmp);
      });
   }
   @Override
   public void onChanged(List<TransactionType> transactionTypes) {
      view.showTransactionList(transactionList, transactionTypes, iconList);
   }
}
