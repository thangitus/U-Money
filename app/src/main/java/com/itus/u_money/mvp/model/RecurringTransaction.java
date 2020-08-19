package com.itus.u_money.mvp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class RecurringTransaction {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public long amount;

    public int transactionTypeId;

    public String note;

    public Date startTime;

    public Date endTime;

    public int gapDays;
}
