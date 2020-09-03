package com.itus.u_money.contract;

import com.itus.u_money.model.Bill;

public interface AddBillContract {
    interface View {
        void showIcon(int resourceId);
        void showDate(String date);
    }

    interface Presenter {
        void saveBill(Bill bill);
        void getResourceId(int iconId);
    }
}
