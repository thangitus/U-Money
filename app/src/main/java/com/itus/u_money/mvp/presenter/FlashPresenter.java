package com.itus.u_money.mvp.presenter;

import com.itus.u_money.mvp.contract.FlashContract;

public class FlashPresenter implements FlashContract.Presenter {
   private final FlashContract.View view;

   public FlashPresenter(FlashContract.View view) {this.view = view;}
}
