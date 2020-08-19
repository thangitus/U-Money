package com.itus.u_money.mvp.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.mvp.model.Bill;

import java.util.List;

@Dao
public interface BillDAO {
    @Query("SELECT * FROM `Bill`")
    List<Bill> getAll();

    @Query("SELECT * FROM `Bill` WHERE id IN (:billIds)")
    List<Bill> loadAllByIds(int[] billIds);

    @Insert
    void insertAll(Bill... bills);

    @Update
    void updateAll(Bill... bills);

    @Delete
    void deleteAll(Bill... bills);
}
