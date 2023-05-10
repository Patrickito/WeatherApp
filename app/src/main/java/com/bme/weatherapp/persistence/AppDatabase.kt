package com.bme.weatherapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bme.weatherapp.model.City


@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}