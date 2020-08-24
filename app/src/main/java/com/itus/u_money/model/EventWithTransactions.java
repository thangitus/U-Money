package com.itus.u_money.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class EventWithTransactions {
    @Embedded public Event event;

    @Relation(
            parentColumn = "id",
            entityColumn = "eventId"
    )
    public List<Transaction> transactions;
}
