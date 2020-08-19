package com.itus.u_money.mvp.model.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.itus.u_money.mvp.model.EventWithTransactions;

import java.util.List;

@Dao
public interface EventWithTransactionsDAO {
    @Transaction
    @Query("SELECT * FROM `Event`")
    public List<EventWithTransactions> getAllEventWithTransactions();

    @Transaction
    @Query("SELECT * FROM `Event` WHERE id = :eventId LIMIT 1")
    public EventWithTransactions getEventWithTransactions(int eventId);
}
