package com.itus.u_money.presenter;

import com.itus.u_money.contract.FlashContract;

public class FlashPresenter implements FlashContract.Presenter {
   private final FlashContract.View view;

   public FlashPresenter(FlashContract.View view) {this.view = view;}
}
