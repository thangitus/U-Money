package com.itus.u_money.contract;

import com.itus.u_money.model.Transaction;

public interface AddBudgetContract {
   interface View {
      void showIcon(int resourceId);
   }

   interface Presenter {
      void saveTransaction(Transaction transaction);

      void getResourceId(int iconId);
   }
}
