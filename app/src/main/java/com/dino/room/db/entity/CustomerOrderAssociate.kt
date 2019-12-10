package com.dino.room.db.entity

import androidx.room.Entity

/**
 * Created by dino9 on 2019-12-10.
 */
@Entity(primaryKeys = ["customerId","orderId"])
data class CustomerOrderAssociate(
  val customerId : Int,
  val orderId : Int
)