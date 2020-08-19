package com.itus.u_money.mvp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TransactionGroup {
    @PrimaryKey
    public int id;

    public String name;
}
