package com.bme.weatherapp.network

import com.bme.weatherapp.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

private const val APY_KEY = "1b5da0bcb7d8dd24681dbf28e19dcb71"

interface WeatherService {
    @GET("weather")
    suspend fun getWeatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = APY_KEY
    ): WeatherData

    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String = APY_KEY
    ): WeatherData

}