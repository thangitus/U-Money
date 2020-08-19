package com.itus.u_money.mvp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class TransactionGroupWithTransactionTypes {
    @Embedded public TransactionGroup transactionGroup;

    @Relation(
            parentColumn = "id",
            entityColumn = "transactionGroupId"
    )
    public List<TransactionType> transactionTypes;
}
