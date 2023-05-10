package com.bme.weatherapp.ui.main

import android.util.Log
import androidx.annotation.WorkerThread
import com.bme.weatherapp.model.City
import com.bme.weatherapp.model.CityData
import com.bme.weatherapp.network.WeatherService
import com.bme.weatherapp.persistence.CityDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val cityDao: CityDao
) {
    fun getAllCitiesStream(): Flow<List<City>> = cityDao.getAllCities()

    fun getAllFavoriteCitiesStream(): Flow<List<City>> = cityDao.getAllFavoriteCities()

    fun getCityStream(id: Int): Flow<City?> = cityDao.getCity(id)

    suspend fun insertCity(item: City) = cityDao.insert(item)

    suspend fun deleteCity(item: City) = cityDao.delete(item)

    suspend fun updateCity(item: City) = cityDao.update(item)
}


