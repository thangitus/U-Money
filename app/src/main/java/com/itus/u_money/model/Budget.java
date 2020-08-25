package com.itus.u_money.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Budget {
   @PrimaryKey(autoGenerate = true)
   public int id;

   public int transactionTypeId;

   public Date startTime;

   public String loopType;

   public long amount;

   public long usedAmount;

   public boolean isRepeated;

    public Budget(int transactionTypeId, Date startTime, String loopType, long amount, long usedAmount, boolean isRepeated) {
        this.transactionTypeId = transactionTypeId;
        this.startTime = startTime;
        this.loopType = loopType;
        this.amount = amount;
        this.usedAmount = usedAmount;
        this.isRepeated = isRepeated;
    }
    public enum LOOP_TYPE {
      Day, Month, Year
   }
}
