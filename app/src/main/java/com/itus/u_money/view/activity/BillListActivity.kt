package com.itus.u_money.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.itus.u_money.contract.BillListContract
import com.itus.u_money.databinding.ActivityBillListBinding
import com.itus.u_money.model.Bill
import com.itus.u_money.presenter.BillListPresenter
import com.itus.u_money.view.adapter.BillAdapter
import kotlinx.coroutines.*
import java.util.*

class BillListActivity : AppCompatActivity(), BillListContract.View {
    var binding: ActivityBillListBinding? = null
    var presenter: BillListContract.Presenter? = null
    val adapter = BillAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBillListBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        presenter = BillListPresenter(this)
        initActionBar()
        binding!!.recyclerview.layoutManager = LinearLayoutManager(this)
        binding!!.recyclerview.itemAnimator = DefaultItemAnimator()
        binding!!.recyclerview.adapter = adapter
        presenter!!.loadData()
    }

    private fun initActionBar() {
        setSupportActionBar(binding!!.toolbar)
        Objects.requireNonNull(supportActionBar)!!.setDisplayHomeAsUpEnabled(true)
        binding!!.toolbar.setNavigationOnClickListener { view -> finish() }
        supportActionBar!!.title = "Hóa đơn"
    }

    override suspend fun setData(items: List<Bill>?) {
        withContext(Dispatchers.Main) {
            adapter.data = items
            adapter.notifyDataSetChanged()
        }
    }
}