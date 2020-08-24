package com.itus.u_money.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.itus.u_money.model.dao.EventWithTransactionsDAO;
import com.itus.u_money.model.dao.IconDAO;
import com.itus.u_money.model.dao.TransactionDAO;
import com.itus.u_money.model.dao.TransactionGroupDAO;
import com.itus.u_money.model.dao.TransactionGroupWithTransactionTypesDAO;
import com.itus.u_money.model.dao.TransactionTypeDAO;
import com.itus.u_money.model.dao.TransactionTypeWithTransactionsDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Transaction.class, TransactionType.class, TransactionGroup.class, Budget.class, Icon.class, Event.class, Bill.class, RecurringTransaction.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "U-Money")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TransactionTypeWithTransactionsDAO transactionTypeWithTransactionsDAO();
    public abstract TransactionGroupWithTransactionTypesDAO transactionGroupWithTransactionTypesDAO();
    public abstract EventWithTransactionsDAO eventWithTransactionsDAO();
    public abstract TransactionDAO transactionDAO();
    public abstract TransactionGroupDAO transactionGroupDAO();
    public abstract TransactionTypeDAO transactionTypeDAO();
    public abstract IconDAO iconDAO();
}
