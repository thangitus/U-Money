package com.itus.u_money.mvp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Icon {
    @PrimaryKey
    @NonNull
    public String path;
}