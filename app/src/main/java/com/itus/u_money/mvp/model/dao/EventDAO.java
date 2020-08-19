package com.itus.u_money.mvp.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.mvp.model.Event;

import java.util.List;

@Dao
public interface EventDAO {
    @Query("SELECT * FROM `Event`")
    List<Event> getAll();

    @Query("SELECT * FROM `Event` WHERE id IN (:eventIds)")
    List<Event> loadAllByIds(int[] eventIds);

    @Insert
    void insertAll(Event... events);

    @Update
    void updateAll(Event... events);

    @Delete
    void deleteAll(Event... events);
}
