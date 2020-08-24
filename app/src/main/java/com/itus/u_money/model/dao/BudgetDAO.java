package com.itus.u_money.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.model.Budget;

import java.util.List;

@Dao
public interface BudgetDAO {
    @Query("SELECT * FROM `Budget`")
    List<Budget> getAll();

    @Query("SELECT * FROM `Budget` WHERE id IN (:budgetIds)")
    List<Budget> loadAllByIds(int[] budgetIds);

    @Insert
    void insertAll(Budget... budgets);

    @Update
    void updateAll(Budget... budgets);

    @Delete
    void deleteAll(Budget... budgets);
}
