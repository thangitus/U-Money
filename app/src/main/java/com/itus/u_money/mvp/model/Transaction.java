package com.itus.u_money.mvp.model;

import androidx.room.PrimaryKey;

import java.util.Date;

public class Transaction {

   @PrimaryKey(autoGenerate = true)
   int id;
   String name;
   Date date;
   long amount;
   String note;
   boolean statusReport;
   int typeTransactionID;
   String pathImg;
   int eventID;
   boolean statusPay;

}
