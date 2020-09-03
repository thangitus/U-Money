package com.itus.u_money.contract

import com.itus.u_money.model.Bill
import com.itus.u_money.view.model.BudgetItem

interface BillListContract {
    interface View {
        fun setData(items: List<Bill>?)
    }

    interface Presenter {
        suspend fun loadData()
    }
}