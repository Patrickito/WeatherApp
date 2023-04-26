package com.bme.weatherapp.model

data class Weather(
    val temperature : Double,
    val conditions : String,
    val windSpeed : Double,
    val airPressure : Double
)
