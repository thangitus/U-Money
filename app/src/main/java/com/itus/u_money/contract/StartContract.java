package com.itus.u_money.contract;

public interface StartContract {
   interface View {
      void onSaveSuccess();
   }

   interface Presenter {
      void saveBudget(long amount);
   }
}
