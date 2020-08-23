package com.itus.u_money.mvp.view.adapter;

import com.itus.u_money.mvp.model.TransactionType;

public interface RecyclerViewListener {
   void onItemClick(TransactionType transactionType);
}
