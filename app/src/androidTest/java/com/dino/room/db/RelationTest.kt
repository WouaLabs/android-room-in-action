package com.dino.room.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.dino.room.db.entity.Customer
import com.dino.room.db.entity.CustomerOrderAssociate
import com.dino.room.db.entity.Order
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by dino9 on 2019-05-14.
 */
@ExperimentalCoroutinesApi
class RelationTest {
  private lateinit var database: Database

  @Before
  fun createDb() {
    database = Room.inMemoryDatabaseBuilder(
      ApplicationProvider.getApplicationContext(),
      Database::class.java
    ).allowMainThreadQueries().build()
  }

  @After
  fun closeDb() {
    database.close()
  }

  @Test
  fun testOneToOneRelation() = runBlocking {

    database.customerDao().insert(Customer(123, "dinesh", "address", "999999999"))
    database.ordersDao().insert(Order(555, 123, "20/11/2019", "100.0"))

    database.customerDao().getCustomerAndOrder().take(1).collect {
      Assert.assertEquals(it.size, 1)
    }
  }

  @Test
  fun testOneToManyRelation() = runBlocking {

    database.customerDao().insert(Customer(123, "dinesh", "address", "999999999"))
    database.ordersDao().insert(Order(555, 123, "20/11/2019", "100.0"))
    database.ordersDao().insert(Order(556, 123, "21/11/2019", "110.0"))


    database.customerDao().getCustomerAndOrders().take(1).collect {
      Assert.assertEquals(it[0].orders.size, 2)
    }
  }

  @Test
  fun testManyToManyRelation() = runBlocking {

    database.customerDao().insert(Customer(123, "woua", "address", "999999999"))
    database.ordersDao().insert(Order(555, 123, "20/11/2019", "100.0"))
    database.ordersDao().insert(Order(556, 123, "21/11/2019", "110.0"))

    database.customerAndOrderDao().insert(CustomerOrderAssociate(123,555))

    database.customerAndOrderDao().insert(CustomerOrderAssociate(123,556))


    database.customerDao().insert(Customer(12345, "woua", "address-1", "1111111111"))
    database.ordersDao().insert(Order(557, 12345, "22/11/2019", "200.0"))
    database.ordersDao().insert(Order(558, 12345, "23/11/2019", "210.0"))

    database.customerAndOrderDao().insert(CustomerOrderAssociate(12345,557))

    database.customerAndOrderDao().insert(CustomerOrderAssociate(12345,558))


    database.customerDao().getCustomersAndOrders().take(1).collect {
      Assert.assertEquals(it.size, 2)
    }
  }
}