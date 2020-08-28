package com.itus.u_money.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.model.Icon;

import java.util.List;

@Dao
public interface IconDAO {
   @Query("SELECT * FROM `Icon`")
   LiveData<List<Icon>> getAll();

   @Query("SELECT resourceId FROM `Icon` WHERE id = :id")
   LiveData<Integer> getResourceIdFromId(int id);

   @Query("SELECT * FROM `Icon` WHERE id = :id")
   Icon getIconById(int id);

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insert(Icon icon);

   @Update
   void updateAll(Icon... icons);

   @Delete
   void deleteAll(Icon... icons);

   @Query("SELECT resourceId FROM `Icon` WHERE id = :iconId")
   int getResourceIdFromIdInt(int iconId);
}
