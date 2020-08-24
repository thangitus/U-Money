package com.itus.u_money.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.model.TransactionType;

import java.util.List;

@Dao
public interface TransactionTypeDAO {
    @Query("SELECT * FROM `TransactionType`")
    List<TransactionType> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TransactionType transactionType);

    @Update
    void update(TransactionType transactionType);
}
