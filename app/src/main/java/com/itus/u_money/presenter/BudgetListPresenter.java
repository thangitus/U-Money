package com.itus.u_money.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.itus.u_money.App;
import com.itus.u_money.contract.BudgetListContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Budget;
import com.itus.u_money.view.model.BudgetItem;

import java.util.List;

public class BudgetListPresenter implements BudgetListContract.Presenter, Observer<List<BudgetItem>> {
    LiveData<List<BudgetItem>> budgetItemsLD;
    BudgetListContract.View view;

    public BudgetListPresenter(BudgetListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadBudgetList() {
        budgetItemsLD = AppDatabase.getDatabase(App.getContext()).budgetDAO().getAllBudgetItem();
        budgetItemsLD.observeForever(this);
    }

    @Override
    public void onChanged(List<BudgetItem> items) {
        view.setData(items);
        budgetItemsLD.removeObserver(this);
    }
}
