package com.itus.u_money.contract;

import com.itus.u_money.model.Event;

public interface AddEventContract {
    interface View {
        void showIcon(int resourceId);
    }

    interface Presenter {
        void getResourceId(int id);
        void saveEvent(Event event);
    }
}
