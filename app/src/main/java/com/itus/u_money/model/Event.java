package com.itus.u_money.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Event {
    @PrimaryKey
    public int id;

    public String name;

    public String iconPath;

    public Date endTime;
}
