package com.itus.u_money.mvp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TransactionType {
    @PrimaryKey
    public int id;

    public boolean isGroupType;

    public String name;

    public int idGroup;
}
