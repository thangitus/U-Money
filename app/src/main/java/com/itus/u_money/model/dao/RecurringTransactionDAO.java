package com.itus.u_money.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.model.RecurringTransaction;

import java.util.List;

@Dao
public interface RecurringTransactionDAO {
    @Query("SELECT * FROM `RecurringTransaction`")
    List<RecurringTransaction> getAll();

    @Query("SELECT * FROM `RecurringTransaction` WHERE id IN (:recurringTransactionIds)")
    List<RecurringTransaction> loadAllByIds(int[] recurringTransactionIds);

    @Insert
    void insertAll(RecurringTransaction... recurringTransactions);

    @Update
    void updateAll(RecurringTransaction... recurringTransactions);

    @Delete
    void deleteAll(RecurringTransaction... recurringTransactions);
}
