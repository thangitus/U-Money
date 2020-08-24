package com.itus.u_money.model.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.itus.u_money.model.TransactionTypeWithTransactions;

import java.util.List;

@Dao
public interface TransactionTypeWithTransactionsDAO {
    @Transaction
    @Query("SELECT * FROM `TransactionType`")
    public List<TransactionTypeWithTransactions> getAllTransactionTypeWithTransactions();

    @Transaction
    @Query("SELECT * FROM `TransactionType` WHERE id = :transactionTypeId LIMIT 1")
    public TransactionTypeWithTransactions getTransactionTypeWithTransactions(int transactionTypeId);
}
