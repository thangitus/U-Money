package com.itus.u_money.presenter;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.itus.u_money.App;
import com.itus.u_money.contract.DetailTransactionContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.model.dao.IconDAO;
import com.itus.u_money.model.dao.TransactionDAO;
import com.itus.u_money.model.dao.TransactionTypeDAO;

public class DetailTransactionPresenter implements DetailTransactionContract.Presenter, Observer<Integer> {
   private final DetailTransactionContract.View view;
   LiveData<Integer> resourceId;

   public DetailTransactionPresenter(DetailTransactionContract.View view) {
      this.view = view;
   }

   @Override
   public void saveTransaction(Transaction transaction) {
      AppDatabase database = AppDatabase.getDatabase(App.getContext());
      TransactionDAO transactionDAO = database.transactionDAO();
      TransactionTypeDAO transactionTypeDAO = database.transactionTypeDAO();

      AppDatabase.executorService.execute(() -> {
         transactionDAO.insertAll(transaction);
         TransactionType transactionType = transactionTypeDAO.getById(transaction.transactionTypeId);
         transactionType.total += transaction.amount;
         transactionTypeDAO.update(transactionType);

         SharedPreferences pref = App.getContext()
                                     .getSharedPreferences("UMoney", 0); // 0 - for private mode
         SharedPreferences.Editor editor = pref.edit();
         long curAmount = pref.getLong("CurAmount", 0);
         if (transactionType.transactionGroupId == 0)
            curAmount -= transaction.amount;
         else
            curAmount += transaction.amount;

         editor.putLong("CurAmount", curAmount);
         editor.apply();
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
