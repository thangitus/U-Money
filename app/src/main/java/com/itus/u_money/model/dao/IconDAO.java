package com.itus.u_money.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.model.Icon;

import java.util.List;

@Dao
public interface IconDAO {
    @Query("SELECT * FROM `Icon`")
    List<Icon> getAll();

    @Query("SELECT * FROM `Icon` WHERE path IN (:iconPaths)")
    List<Icon> loadAllByPaths(String[] iconPaths);

    @Insert
    void insertAll(Icon... icons);

    @Update
    void updateAll(Icon... icons);

    @Delete
    void deleteAll(Icon... icons);
}
