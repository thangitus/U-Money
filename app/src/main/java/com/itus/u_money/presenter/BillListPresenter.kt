package com.itus.u_money.presenter

import com.itus.u_money.App
import com.itus.u_money.contract.BillListContract
import com.itus.u_money.model.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class BillListPresenter(val view: BillListContract.View) : BillListContract.Presenter {

    override suspend fun loadData() {
        coroutineScope {
            launch {
                val billList = AppDatabase.getDatabase(App.getContext()).billDAO().all
                view.setData(billList)
            }
        }
    }
}