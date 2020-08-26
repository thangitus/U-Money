package com.itus.u_money.contract;

import com.itus.u_money.model.Icon;
import com.itus.u_money.view.model.DataBarChart;

import java.util.List;

public interface ReportContract extends BaseCardContract {
   interface View extends BaseCardContract.View {
      void updateChartData(List<DataBarChart> dataBarCharts, boolean animate);

      void updateInOut(long in, long out);
      void updateLoan(long loan,long borrow);
   }

   interface Presenter extends BaseCardContract.Presenter {
      void getData();
   }
}
