package com.itus.u_money.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.itus.u_money.App;
import com.itus.u_money.contract.BudgetListContract;
import com.itus.u_money.contract.EventListContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.view.model.BudgetItem;
import com.itus.u_money.view.model.EventItem;

import java.util.List;

public class EventListPresenter implements EventListContract.Presenter, Observer<List<EventItem>> {
    LiveData<List<EventItem>> eventItemsLD;
    EventListContract.View view;

    public EventListPresenter(EventListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadEventList() {
        eventItemsLD = AppDatabase.getDatabase(App.getContext()).eventDAO().getAllEventItems();
        eventItemsLD.observeForever(this);
    }

    @Override
    public void onChanged(List<EventItem> items) {
        view.setData(items);
        eventItemsLD.removeObserver(this);
    }
}
