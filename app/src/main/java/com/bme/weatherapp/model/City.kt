package com.bme.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "cityName") var cityName: String,
    @ColumnInfo(name = "fav") var fav: Boolean,
    @ColumnInfo(name = "main") var main: String,
    @ColumnInfo(name = "temp") var temp: Double,
    @ColumnInfo(name = "pressure") var pressure: Int,
    @ColumnInfo(name = "wind") var wind: Double
)
