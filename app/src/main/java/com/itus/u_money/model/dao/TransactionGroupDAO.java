package com.itus.u_money.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.model.TransactionGroup;

import java.util.List;

@Dao
public interface TransactionGroupDAO {
    @Query("SELECT * FROM `TransactionGroup`")
    List<TransactionGroup> getAll();

    @Insert(
            onConflict = OnConflictStrategy.REPLACE
    )
    void insert(TransactionGroup transactionGroup);

    @Update
    void update(TransactionGroup transactionGroup);
}
