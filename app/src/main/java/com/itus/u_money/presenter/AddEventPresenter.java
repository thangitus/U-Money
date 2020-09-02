package com.itus.u_money.presenter;

import com.itus.u_money.App;
import com.itus.u_money.contract.AddEventContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Event;

public class AddEventPresenter implements AddEventContract.Presenter {
    @Override
    public void getResourceId(int id) {

    }

    @Override
    public void saveEvent(Event event) {
        AppDatabase.executorService.execute(()-> {
            AppDatabase.getDatabase(App.getContext()).eventDAO().insertAll(event);
        });
    }
}
