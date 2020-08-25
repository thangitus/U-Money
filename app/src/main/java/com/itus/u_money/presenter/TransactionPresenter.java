package com.itus.u_money.presenter;

import com.itus.u_money.contract.TransactionContract;

public class TransactionPresenter extends BaseCardPresenter implements TransactionContract.Presenter {

   public TransactionPresenter(TransactionContract.View view) {
      super(view);
   }
}
