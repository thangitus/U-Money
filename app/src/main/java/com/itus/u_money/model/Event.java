package com.itus.u_money.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Event {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public int iconId;

    public Date endTime;
}
