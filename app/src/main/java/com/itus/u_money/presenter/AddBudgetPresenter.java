package com.itus.u_money.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.itus.u_money.App;
import com.itus.u_money.contract.AddBudgetContract;
import com.itus.u_money.contract.AddTransactionContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.dao.IconDAO;

public class AddBudgetPresenter implements AddBudgetContract.Presenter, Observer<Integer> {
    LiveData<Integer> resourceId;
    AddBudgetContract.View view;

    public AddBudgetPresenter(AddBudgetContract.View view) {
        this.view = view;
    }

    @Override
    public void saveTransaction(Transaction transaction) {

    }

    @Override
    public void getResourceId(int iconId) {
        IconDAO iconDAO = AppDatabase.getDatabase(App.getContext())
                .iconDAO();
        resourceId = iconDAO.getResourceIdFromId(iconId);
        resourceId.observeForever(this);
    }

    @Override
    public void onChanged(Integer integer) {
        view.showIcon(integer);
        resourceId.removeObserver(this);
    }
}
