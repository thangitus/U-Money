package com.itus.u_money.presenter;

import com.itus.u_money.contract.BaseCardContract;
import com.itus.u_money.contract.ReportContract;

public class ReportPresenter extends BaseCardPresenter implements ReportContract.Presenter {

   public ReportPresenter(BaseCardContract.View view) {
      super(view);
   }
}
