package com.dino.room.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by dino9 on 26/03/19.
 */
@Entity(tableName = "customers")
//One to many relationship with address
data class Customer(@PrimaryKey val customerId: Int, val name: String, val address: String, val contactNumber: String)