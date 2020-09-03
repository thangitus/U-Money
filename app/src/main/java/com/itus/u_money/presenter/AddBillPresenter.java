package com.itus.u_money.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.itus.u_money.App;
import com.itus.u_money.contract.AddBillContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Bill;

public class AddBillPresenter implements AddBillContract.Presenter, Observer<Integer> {
    LiveData<Integer> resourceId;
    AddBillContract.View view;

    public AddBillPresenter(AddBillContract.View view) {
        this.view = view;
    }

    @Override
    public void saveBill(Bill bill) {
        AppDatabase.executorService.execute(()-> {
            AppDatabase.getDatabase(App.getContext()).billDAO().insertAll(bill);
        });
    }

    @Override
    public void getResourceId(int iconId) {
        resourceId = AppDatabase.getDatabase(App.getContext()).iconDAO().getResourceIdFromId(iconId);
        resourceId.observeForever(this);
    }

    @Override
    public void onChanged(Integer integer) {
        view.showIcon(integer);
        resourceId.removeObserver(this);
    }
}
