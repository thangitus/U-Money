package com.itus.u_money.mvp.presenter;

import com.itus.u_money.mvp.contract.TransactionContract;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TransactionPresenter implements TransactionContract.Presenter {
   private final TransactionContract.View view;
   int curReportInt = 2;
   REPORT_BY curReportBy = REPORT_BY.Month;
   Date date = Calendar.getInstance()
                       .getTime();

   public TransactionPresenter(TransactionContract.View view) {
      this.view = view;
   }

   @Override
   public void onUpClick() {
      if (curReportInt == 3)
         curReportInt = 1;
      else
         curReportInt++;
      view.updateReportBy(getReportBy(), true);
      view.updateDate(getCurrentFormatDay().format(date));
      view.animateChart();
   }

   @Override
   public void onDownClick() {
      if (curReportInt == 1)
         curReportInt = 3;
      else
         curReportInt--;
      view.updateReportBy(getReportBy(), false);
      view.updateDate(getCurrentFormatDay().format(date));
      view.animateChart();
   }

   @Override
   public void onViewCreated() {
      view.updateReportBy(getReportBy(), false);
      view.updateDate(getCurrentFormatDay().format(date));
   }
   @Override
   public void onNextClick() {
      updateDate(1);
      view.updateDate(getCurrentFormatDay().format(date));
      view.upDateChart(true);
   }
   @Override
   public void onPrevClick() {
      updateDate(-1);
      view.updateDate(getCurrentFormatDay().format(date));
      view.upDateChart(false);
   }

   @Override
   public void onMoveCurDateClick() {
      Date date = Calendar.getInstance()
                          .getTime();
      int compare = this.date.compareTo(date);

      if (compare > 0)
         view.upDateChart(false);
      else
         view.upDateChart(true);
      this.date = date;
   }

   private void updateDate(int value) {
      LocalDate localDate = date.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
      switch (curReportBy) {
         case Day:
            localDate = localDate.plusDays(value);
            break;
         case Month:
            localDate = localDate.plusMonths(value);
            break;
         case Year:
            localDate = localDate.plusYears(value);
      }
      date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault())
                                .toInstant());
   }
   public enum REPORT_BY {
      Day, Month, Year
   }

   private String getReportBy() {
      switch (curReportInt) {
         case 1:
            curReportBy = REPORT_BY.Day;
            return "Ngày";
         case 2:
            curReportBy = REPORT_BY.Month;
            return "Tháng";
         case 3:
            curReportBy = REPORT_BY.Year;
            return "Năm";
         default:
            return "Err";
      }
   }
   private SimpleDateFormat getCurrentFormatDay() {
      String myFormat;
      switch (curReportBy) {
         case Day:
            myFormat = "dd-MM-yyyy";
            break;
         case Month:
            myFormat = "MM-yyyy";
            break;
         default:
            myFormat = "yyyy";
      }
      return new SimpleDateFormat(myFormat, Locale.US);
   }
}
