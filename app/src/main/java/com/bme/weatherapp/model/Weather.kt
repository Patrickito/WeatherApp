package com.bme.weatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val main: String
)
