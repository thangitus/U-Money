package com.itus.u_money.presenter;

import com.itus.u_money.App;
import com.itus.u_money.contract.StartContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Budget;
import com.itus.u_money.model.dao.BudgetDAO;

import java.util.Calendar;

public class StartPresenter implements StartContract.Presenter {
   private final StartContract.View view;
   public StartPresenter(StartContract.View view) {this.view = view;}

   @Override
   public void saveBudget(long amount) {
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.DAY_OF_MONTH, 1);
      Budget budget = new Budget(7, calendar.getTime(), Budget.LOOP_TYPE.Month.toString(), amount, 0, true);
      BudgetDAO budgetDAO = AppDatabase.getDatabase(App.getContext())
                                       .budgetDAO();
      AppDatabase.executorService.execute(() -> {
         budgetDAO.insertAll(budget);
      });
      view.onSaveSuccess();
   }
}
