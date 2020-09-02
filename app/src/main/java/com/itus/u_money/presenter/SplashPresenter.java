package com.itus.u_money.presenter;

import com.itus.u_money.contract.FlashContract;

public class SplashPresenter implements FlashContract.Presenter {
   private final FlashContract.View view;

   public SplashPresenter(FlashContract.View view) {this.view = view;}
}
