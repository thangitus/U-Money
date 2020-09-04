package com.itus.u_money.presenter

import com.itus.u_money.App
import com.itus.u_money.contract.BillListContract
import com.itus.u_money.model.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BillListPresenter(val view: BillListContract.View) : BillListContract.Presenter {

    override fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
//            delay(2000)
            val billList = AppDatabase.getDatabase(App.getContext()).billDAO().getAll()
            view.setData(billList)
        }
    }
}