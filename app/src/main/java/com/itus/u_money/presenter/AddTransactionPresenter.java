package com.itus.u_money.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.itus.u_money.App;
import com.itus.u_money.contract.AddTransactionContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.dao.IconDAO;
import com.itus.u_money.model.dao.TransactionDAO;

public class AddTransactionPresenter implements AddTransactionContract.Presenter, Observer<Integer> {
   private final AddTransactionContract.View view;
   LiveData<Integer> resourceId;

   public AddTransactionPresenter(AddTransactionContract.View view) {
      this.view = view;
   }

   @Override
   public void saveTransaction(Transaction transaction) {
      TransactionDAO transactionDAO = AppDatabase.getDatabase(App.getContext())
                                                 .transactionDAO();
      AppDatabase.executorService.execute(() -> {
         transactionDAO.insertAll(transaction);
      });
   }

   @Override
   public void getResourceId(int iconId) {
      IconDAO iconDAO = AppDatabase.getDatabase(App.getContext())
                                   .iconDAO();
      resourceId = iconDAO.getResourceIdFromId(iconId);
      resourceId.observeForever(this);
   }
   @Override
   public void onChanged(Integer integer) {
      view.showIconTransaction(integer);
      resourceId.removeObserver(this);
   }
}
