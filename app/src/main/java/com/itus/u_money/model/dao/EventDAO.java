package com.itus.u_money.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.itus.u_money.model.Event;
import com.itus.u_money.view.model.EventItem;

import java.util.List;

@Dao
public interface EventDAO {
    @Query("SELECT * FROM `Event`")
    List<Event> getAll();

    @Query("SELECT i.resourceId icon, e.name name, e.endTime date FROM `Event` e, `Icon` i WHERE e.iconId == i.id")
    LiveData<List<EventItem>> getAllEventItems();

    @Query("SELECT * FROM `Event` WHERE id IN (:eventIds)")
    List<Event> loadAllByIds(int[] eventIds);

    @Insert
    void insertAll(Event... events);

    @Update
    void updateAll(Event... events);

    @Delete
    void deleteAll(Event... events);
}
