package com.itus.u_money.contract;

import com.itus.u_money.view.model.EventItem;

import java.util.List;

public interface EventListContract {
    interface View {
        void setData(List<EventItem> items);
    }

    interface Presenter {
        void loadEventList();
    }
}
