package com.itus.u_money.contract;

import com.itus.u_money.model.Icon;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;

import java.util.List;

public interface TransactionContract {
   interface View extends BaseCardContract.View {
      void showTransactionList(List<Transaction> transactionList, List<TransactionType> transactionTypes, List<Icon> icons);
   }

   interface Presenter extends BaseCardContract.Presenter {
      void getData();
   }

}
