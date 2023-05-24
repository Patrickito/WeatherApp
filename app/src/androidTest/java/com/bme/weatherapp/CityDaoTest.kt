package com.bme.weatherapp

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bme.weatherapp.model.City
import com.bme.weatherapp.persistence.AppDatabase
import com.bme.weatherapp.persistence.CityDao
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//@RunWith(AndroidJUnit4::class)
//class Tests {
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.bme.weatherapp", appContext.packageName)
//    }
//}

@RunWith(AndroidJUnit4::class)
class CityDaoTest {


    private lateinit var cityDao: CityDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        cityDao = db.cityDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun insertAndLoadCityTest() {

        val city = City(1, "Budapest", false, "Clear", 210.0, 2000, 10.0)

        cityDao.insert(city)

        val city_ = cityDao.getCity(1)
        assertThat(city_.cityName, equalTo(city.cityName))
    }
}
