package com.itus.u_money.model.dao

import androidx.room.*
import com.itus.u_money.model.Bill

@Dao
interface BillDAO {
    @Query("SELECT * FROM `Bill`")
     fun getAll(): List<Bill>?

    @Query("SELECT * FROM `Bill` WHERE id IN (:billIds)")
    fun loadAllByIds(billIds: IntArray?): List<Bill?>?

    @Insert
    fun insertAll(vararg bills: Bill?)

    @Update
    fun updateAll(vararg bills: Bill?)

    @Delete
    fun deleteAll(vararg bills: Bill?)
}