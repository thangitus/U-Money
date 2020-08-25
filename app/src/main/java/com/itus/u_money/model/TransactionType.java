package com.itus.u_money.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class TransactionType implements Serializable {
   @PrimaryKey
   public int id;

   public long total;

   public String name;

   public int transactionGroupId;

   public int iconId;

   public TransactionType(int id, long total, String name, int transactionGroupId, int iconId) {
      this.id = id;
      this.total = total;
      this.name = name;
      this.transactionGroupId = transactionGroupId;
      this.iconId = iconId;
   }

   public int getId() {
      return id;
   }
   public String getName() {
      return name;
   }
}
