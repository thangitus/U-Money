package com.itus.u_money.model.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.model.TransactionGroup;

import java.util.List;

@Dao
public interface TransactionGroupDAO {
    @Query("SELECT * FROM `TransactionGroup`")
    List<TransactionGroup> getAll();

    @Update
    void updateAll(TransactionGroup... transactionGroups);
}
