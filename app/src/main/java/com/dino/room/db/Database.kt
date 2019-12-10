package com.dino.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dino.room.db.dao.CustomerAndOrderDao
import com.dino.room.db.dao.CustomersDao
import com.dino.room.db.dao.OrdersDao
import com.dino.room.db.entity.Customer
import com.dino.room.db.entity.CustomerAndOrder
import com.dino.room.db.entity.CustomerOrderAssociate
import com.dino.room.db.entity.Order

/**
 * Created by dino9 on 2019-05-14.
 */

@Database(
  entities = [Customer::class, Order::class, CustomerOrderAssociate::class],
  version = 1
)
abstract class Database : RoomDatabase() {

  abstract fun customerDao(): CustomersDao

  abstract fun ordersDao(): OrdersDao

  abstract fun customerAndOrderDao(): CustomerAndOrderDao

  companion object {

    private const val DB_NAME = "consumer.db"

    @Volatile
    private var INSTANCE: com.dino.room.db.Database? = null

    fun getInstance(context: Context): com.dino.room.db.Database = INSTANCE ?: synchronized(this) {
      INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
    }

    private fun buildDatabase(context: Context) = Room.databaseBuilder(
      context.applicationContext, com.dino.room.db.Database::class.java, DB_NAME
    ).build()

  }
}
