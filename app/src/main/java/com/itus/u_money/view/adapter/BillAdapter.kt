package com.itus.u_money.view.adapter

import android.annotation.SuppressLint
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itus.u_money.App
import com.itus.u_money.databinding.ItemBillBinding
import com.itus.u_money.model.AppDatabase
import com.itus.u_money.model.Bill

class BillAdapter() : RecyclerView.Adapter<BillAdapter.ViewHolder>() {
    var data: List<Bill>? = null

    class ViewHolder(val item: ItemBillBinding) : RecyclerView.ViewHolder(item.root), View.OnClickListener {
        val handler = Handler()

        init {

        }

        @SuppressLint("SetTextI18n")
        fun bind(bill: Bill) {
            item.amount.text = bill.amount.toString() + " VND"
            var loopType = "Lặp theo "
            loopType += when (bill.loopType) {
                "DAY" -> "ngày"
                "WEEK" -> "tuần"
                "MONTH" -> "tháng"
                "YEAR" -> "năm"
                else -> ""
            }
            item.textLoopType.text = loopType

            val appDatabase = AppDatabase.getDatabase(App.getContext())
            AppDatabase.executorService.execute {
                val transactionType = appDatabase
                        .transactionTypeDAO()
                        .getById(bill.typeId)
                val icon = appDatabase.iconDAO().getIconById(transactionType.iconId)
                handler.post {
                    item.billIcon.setImageResource(icon.resourceId)
                    item.billName.text = transactionType.name
                }
            }
        }


        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemBillBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bill = data!![position]
        holder.bind(bill)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}