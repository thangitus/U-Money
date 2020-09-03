package com.itus.u_money.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
class Transaction:Serializable {
    @JvmField
    @PrimaryKey(autoGenerate = true)
    var id = 0
    @JvmField
    var date: Date? = null
    @JvmField
    var amount: Long? = 0
    @JvmField
    var note: String? = null
    @JvmField
    var reportingStatus = false
    @JvmField
    var transactionTypeId = 0
    @JvmField
    var imagePath: String? = null
    @JvmField
    var eventId = 0
    @JvmField
    var paymentStatus = false


}