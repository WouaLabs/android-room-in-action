package com.dino.room.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by dino9 on 26/03/19.
 */
@Entity(tableName = "orders")
//One to many relationship with address
data class Order(@PrimaryKey val orderId: Int, val orderCustomerId: Int, val date: String, val amount: String)