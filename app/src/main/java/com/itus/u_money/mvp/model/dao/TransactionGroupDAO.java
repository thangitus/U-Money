package com.itus.u_money.mvp.model.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.mvp.model.TransactionGroup;
import com.itus.u_money.mvp.model.TransactionType;

import java.util.List;

@Dao
public interface TransactionGroupDAO {
    @Query("SELECT * FROM `TransactionGroup`")
    List<TransactionGroup> getAll();

    @Update
    void updateAll(TransactionGroup... transactionGroups);
}
