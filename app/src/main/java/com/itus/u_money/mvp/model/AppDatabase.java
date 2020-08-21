package com.itus.u_money.mvp.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.itus.u_money.mvp.model.dao.EventWithTransactionsDAO;
import com.itus.u_money.mvp.model.dao.TransactionDAO;
import com.itus.u_money.mvp.model.dao.TransactionGroupWithTransactionTypesDAO;
import com.itus.u_money.mvp.model.dao.TransactionTypeWithTransactionsDAO;

@Database(entities = {Transaction.class, TransactionType.class, TransactionGroup.class, Budget.class, Icon.class, Event.class, Bill.class, RecurringTransaction.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransactionTypeWithTransactionsDAO transactionTypeWithTransactionsDAO();
    public abstract TransactionGroupWithTransactionTypesDAO transactionGroupWithTransactionTypesDAO();
    public abstract EventWithTransactionsDAO eventWithTransactionsDAO();
    public abstract TransactionDAO transactionDAO();
}
