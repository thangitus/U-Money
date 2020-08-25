package com.itus.u_money.contract;

public interface BaseCardContract {
   interface View {
      void updateReportBy(String text, boolean increase);

      void updateDate(String text);

      void upDateChart(boolean increase);
      void animateChart();
   }

   interface Presenter {
      void onUpClick();

      void onDownClick();

      void onViewCreated();

      void onNextClick();

      void onPrevClick();

      void onMoveCurDateClick();
   }
}
