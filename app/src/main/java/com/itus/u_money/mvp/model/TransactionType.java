package com.itus.u_money.mvp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TransactionType {
   @PrimaryKey
   public int id;
   public long total;

   public String name;
   public TransactionType() {
   }
   public int transactionGroupId;
   public TransactionType(String name, int iconId) {
      this.name = name;
      this.iconId = iconId;
   }

   public enum GROUP_TYPE {
      Income, Outgoing, Loan, Borrow
   }

   public int iconId;

   public int getId() {
      return id;
   }
   public String getName() {
      return name;
   }
}
