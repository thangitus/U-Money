package com.itus.u_money.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Icon {
   @PrimaryKey
   @NonNull
   public int id;

   public int resourceId;

   public String backgroundColor;
    public Icon(int id, int resourceId, String backgroundColor) {
        this.id = id;
        this.resourceId = resourceId;
        this.backgroundColor = backgroundColor;
    }

}
