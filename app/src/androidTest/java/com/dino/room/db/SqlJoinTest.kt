package com.dino.room.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.dino.room.db.entity.Customer
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
class SqlJoinTest {
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
  fun testInnerJoin() = runBlocking {

    database.customerDao().insert(Customer(123, "dinesh", "address", "999999999"))
    database.ordersDao().insert(Order(555, 123, "20/11/2019", "100.0"))

    database.ordersDao().getCustomerNameUsingInnerJoin().take(1).collect {
      Assert.assertEquals(it[0], "dinesh")
    }
  }

  @Test
  fun testLeftJoin() = runBlocking {
  }

  @Test
  fun testRightJoin() = runBlocking {
  }

  @Test
  fun testFullJoin() = runBlocking {
  }

  @Test
  fun testSelfJoin() = runBlocking {
  }

}