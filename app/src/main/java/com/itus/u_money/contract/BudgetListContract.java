package com.itus.u_money.contract;

import com.itus.u_money.model.Budget;
import com.itus.u_money.view.model.BudgetItem;

import java.util.List;

public interface BudgetListContract {
    interface View {
        void setData(List<BudgetItem> items);
    }

    interface Presenter {
        void loadBudgetList();
    }
}
