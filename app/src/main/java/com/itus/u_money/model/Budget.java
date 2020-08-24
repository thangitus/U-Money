package com.itus.u_money.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Budget {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int transactionTypeId;

    public Date startTime;

    public Date endTime;

    public long amount;

    public long usedAmount;

    public boolean isRepeated;
}
