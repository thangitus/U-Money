package com.itus.u_money.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Transaction {
   @PrimaryKey(autoGenerate = true)
   public int id;

   public Date date;

   public long amount;

   public String note;

   public boolean reportingStatus;

   public int transactionTypeId;

   public String imagePath;

   public int eventId;

   public boolean paymentStatus;
}
