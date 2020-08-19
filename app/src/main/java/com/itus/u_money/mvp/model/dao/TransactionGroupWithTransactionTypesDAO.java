package com.itus.u_money.mvp.model.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.itus.u_money.mvp.model.TransactionGroupWithTransactionTypes;
import com.itus.u_money.mvp.model.TransactionTypeWithTransactions;

import java.util.List;

@Dao
public interface TransactionGroupWithTransactionTypesDAO {
    @Transaction
    @Query("SELECT * FROM `TransactionGroup`")
    public List<TransactionGroupWithTransactionTypes> getAllTransactionGroupWithTransactionTypes();

    @Transaction
    @Query("SELECT * FROM `TransactionGroup` WHERE id = :transactionGroupId LIMIT 1")
    public TransactionGroupWithTransactionTypes getTransactionGroupWithTransactionTypes(int transactionGroupId);
}
