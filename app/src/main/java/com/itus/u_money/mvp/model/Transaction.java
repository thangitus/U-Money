package com.itus.u_money.mvp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Transaction {
   @PrimaryKey(autoGenerate = true)
   public int id;

   public String name;

   public Date date;

   public long amount;

   public String note;

   public boolean statusReport;

   public int typeTransactionID;

   public String pathImg;

   public int eventID;

   public boolean statusPay;
}
