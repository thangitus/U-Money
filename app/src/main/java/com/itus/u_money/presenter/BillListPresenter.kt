package com.itus.u_money.presenter

import android.util.Log
import com.itus.u_money.App
import com.itus.u_money.contract.BillListContract
import com.itus.u_money.model.AppDatabase
import kotlinx.coroutines.*

class BillListPresenter(val view: BillListContract.View) : BillListContract.Presenter {

    private val TAG="BillListPresenter"

    override fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
//            delay(1000)
            val billList = AppDatabase.getDatabase(App.getContext()).billDAO().getAll()
            val transactionType = AppDatabase.getDatabase(App.getContext()).transactionTypeDAO().getById(billList!![0].typeId)
            delay(2000)
            Log.d(TAG, "loadData: ${transactionType.name}")
            view.setData(billList)
        }
    }
}