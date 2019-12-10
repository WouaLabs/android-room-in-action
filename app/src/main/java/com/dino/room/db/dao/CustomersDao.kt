package com.dino.room.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.dino.room.db.entity.Customer
import com.dino.room.db.entity.CustomerAndOrder
import com.dino.room.db.entity.CustomerWithOrders
import com.dino.room.db.entity.CustomersWithOrders
import kotlinx.coroutines.flow.Flow

/**
 * Created by dino9 on 2019-05-14.
 */
@Dao
interface CustomersDao : BaseDao <Customer>{
  @Transaction
  @Query("SELECT * FROM customers")
  fun getCustomerAndOrder(): Flow<MutableList<CustomerAndOrder>>

  @Transaction
  @Query("SELECT * FROM customers")
  fun getCustomerAndOrders(): Flow<MutableList<CustomerWithOrders>>

  @Transaction
  @Query("SELECT * FROM customers")
  fun getCustomersAndOrders(): Flow<MutableList<CustomersWithOrders>>
}