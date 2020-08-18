package com.itus.u_money.mvp.model.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.itus.u_money.mvp.model.TransactionTypeWithTransactions;

import java.util.List;

@Dao
public interface TransactionTypeWithTransactionsDAO {
    @Transaction
    @Query("SELECT * FROM TransactionType")
    public List<TransactionTypeWithTransactions> getTransactionTypeWithTransactions();
}
