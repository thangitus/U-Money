package com.itus.u_money.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.model.Budget;
import com.itus.u_money.view.model.BudgetItem;

import java.util.List;

@Dao
public interface BudgetDAO {
    @Query("SELECT * FROM `Budget`")
    List<Budget> getAll();

    @Query("SELECT i.resourceId icon, t.name type, b.amount amount  FROM `Budget` b, `TransactionType` t, `Icon` i WHERE b.transactionTypeId == t.id AND t.iconId == i.id")
    LiveData<List<BudgetItem>>  getAllBudgetItem();

    @Query("SELECT * FROM `Budget` WHERE id IN (:budgetIds)")
    List<Budget> loadAllByIds(int[] budgetIds);

    @Insert
    void insertAll(Budget... budgets);

    @Update
    void updateAll(Budget... budgets);

    @Delete
    void deleteAll(Budget... budgets);
}
