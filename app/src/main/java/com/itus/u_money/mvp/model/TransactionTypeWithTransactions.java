package com.itus.u_money.mvp.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;

public class TransactionTypeWithTransactions {
    @Embedded public TransactionType transactionType;
    @Relation(
            parentColumn = "id",
            entityColumn = "typeTransactionID"
    )
    public List<Transaction> transactions;
}
