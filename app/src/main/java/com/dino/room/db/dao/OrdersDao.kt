package com.dino.room.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.dino.room.db.entity.Order
import kotlinx.coroutines.flow.Flow

/**
 * Created by dino9 on 2019-05-14.
 */
@Dao
interface OrdersDao : BaseDao<Order>{
  @Transaction
  @Query("SELECT custom.name FROM orders AS ord INNER JOIN customers as custom ON ord.orderCustomerId = custom.customerId")
  fun getCustomerNameUsingInnerJoin(): Flow<MutableList<String>>
}