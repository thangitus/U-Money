package com.itus.u_money.contract;

import com.itus.u_money.model.TransactionType;

import java.util.List;

public interface TypeContract {
   interface View {
      void showData(List<TransactionType> transactionTypeList);
   }

   interface Presenter {
      void getData(int index);

      void setType(String string);
   }
}
