package com.bme.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class CityData(
    val weather: Weather,
    val id: Int = 0,
    var cityName: String,
    var fav: Boolean
)
