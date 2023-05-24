package com.bme.weatherapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Location (
    val lon: Double,
    val lat: Double
)
