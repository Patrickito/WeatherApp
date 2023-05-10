package com.bme.weatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Location (
    val weatherData: WeatherData,
    val lat: Double
)
