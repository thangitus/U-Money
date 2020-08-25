package com.itus.u_money.model.dao;

import androidx.lifecycle.LiveData;
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
   LiveData<List<TransactionType>> getAll();

   @Query("SELECT * FROM `TransactionType` WHERE transactionGroupId=:id")
   LiveData<List<TransactionType>> getByGroupId(int id);

   @Query("SELECT * FROM `TransactionType` WHERE id=:id LIMIT 1")
   TransactionType getById(int id);

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insert(TransactionType transactionType);

   @Update
   void update(TransactionType transactionType);
}
