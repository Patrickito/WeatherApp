package com.bme.weatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Main(
    val temp: Double,
    val pressure: Int
)
