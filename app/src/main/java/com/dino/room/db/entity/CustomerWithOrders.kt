package com.dino.room.db.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Created by dino9 on 2019-12-09.
 */
data class CustomerWithOrders(
  @Embedded val customer : Customer,
  @Relation(
    parentColumn = "customerId",
    entityColumn = "orderCustomerId"
  )
  val orders: List<Order>
)