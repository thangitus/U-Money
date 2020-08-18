package com.itus.u_money.mvp.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.itus.u_money.mvp.model.dao.TransactionTypeWithTransactionsDAO;

@Database(entities = {Transaction.class, TransactionType.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransactionTypeWithTransactionsDAO transactionTypeWithTransactionsDAO();
}
