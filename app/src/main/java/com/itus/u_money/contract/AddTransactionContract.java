package com.itus.u_money.contract;

import com.itus.u_money.model.Transaction;

public interface AddTransactionContract {
   interface View {
      void showIconTransaction(int resourceId);
   }

   interface Presenter {
      void saveTransaction(Transaction transaction);

      void getResourceId(int iconId);
   }
}
