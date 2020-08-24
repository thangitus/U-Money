package com.itus.u_money.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TransactionTypeWithTransactions {
    @Embedded public TransactionType transactionType;
    @Relation(
            parentColumn = "id",
            entityColumn = "transactionTypeId"
    )
    public List<Transaction> transactions;
}
