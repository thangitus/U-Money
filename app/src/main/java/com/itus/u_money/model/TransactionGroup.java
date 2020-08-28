package com.itus.u_money.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TransactionGroup {
    @PrimaryKey
    public int id;

    public String name;

    public TransactionGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
