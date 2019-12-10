package com.dino.room.db.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * Created by dino9 on 2019-12-09.
 */
data class CustomersWithOrders(
  @Embedded val customer : Customer,
  @Relation(
    parentColumn = "customerId",
    entityColumn = "orderId",
    associateBy = Junction(CustomerOrderAssociate::class)
  )
  val orders: List<Order>
)