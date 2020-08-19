package com.itus.u_money.mvp.model.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.mvp.model.TransactionType;

import java.util.List;

@Dao
public interface TransactionTypeDAO {
    @Query("SELECT * FROM `TransactionType`")
    List<TransactionType> getAll();

    @Update
    void updateAll(TransactionType... transactionTypes);
}
