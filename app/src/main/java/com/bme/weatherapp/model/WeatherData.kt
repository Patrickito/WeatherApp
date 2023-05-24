package com.bme.weatherapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val coord: Location,
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
)
