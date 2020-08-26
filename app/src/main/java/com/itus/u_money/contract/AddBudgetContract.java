package com.itus.u_money.contract;

import com.itus.u_money.model.Budget;

public interface AddBudgetContract {
   interface View {
      void showIcon(int resourceId);
   }

   interface Presenter {
      void saveBudget(Budget budget);

      void getResourceId(int iconId);
   }
}
