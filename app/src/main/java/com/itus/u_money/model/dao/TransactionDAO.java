package com.itus.u_money.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.model.Transaction;

import java.util.List;

@Dao
public interface TransactionDAO {
    @Query("SELECT * FROM `Transaction`")
    List<Transaction> getAll();

    @Query("SELECT * FROM `Transaction` WHERE id IN (:transactionIds)")
    List<Transaction> loadAllByIds(int[] transactionIds);

    @Insert
    void insertAll(Transaction... transactions);

    @Update
    void updateAll(Transaction... transactions);

    @Delete
    void deleteAll(Transaction... transactions);
}
