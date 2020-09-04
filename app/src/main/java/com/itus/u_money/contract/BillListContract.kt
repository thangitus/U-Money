package com.itus.u_money.contract

import com.itus.u_money.model.Bill

interface BillListContract {
    interface View {
        suspend fun setData(items: List<Bill>?)
    }

    interface Presenter {
         fun loadData()
    }
}