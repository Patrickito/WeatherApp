package com.bme.weatherapp.ui.main

import android.util.Log
import androidx.annotation.WorkerThread
import com.bme.weatherapp.gps.LocationService
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
    private val locationService: LocationService,
    private val cityDao: CityDao
) {

    private val initList = listOf("Budapest", "New York", "Tokyo", "London", "Moscow", "Paris", "Los Angeles", "Berlin", "Rome", "Madrid", "Vienna", "Barcelona", "Prague", "Milan", "Amsterdam", "Bucharest", "Brussels", "Hamburg", "Munich", "Stockholm")

    fun getLocation(): Pair<Double, Double>? = locationService.getLonLat()

    suspend fun addFavorite(id: Long) = flow {
        val city = cityDao.getCity(id)
        city.fav = true
        cityDao.update(city)
        emit(city)
    }


    fun getAllCitiesStream() = flow {
        val cities: List<City> = cityDao.getAllCities()
        if (cities.isEmpty()) {

            initList.forEach {
                val data = weatherService.getWeatherByCity(it)

                cityDao.insert(City(
                    cityName = it,
                    fav = false,
                    main = data.weather.first().main,
                    temp = data.main.temp - 273.15,
                    pressure = data.main.pressure,
                    wind = data.wind.speed));
            }
        }
        emit(cities)
    }


    fun getAllFavoriteCitiesStream(): List<City> = cityDao.getAllFavoriteCities()

    fun getCityStream(id: Long): City = cityDao.getCity(id)

    suspend fun insertCity(item: City) = cityDao.insert(item)

    suspend fun deleteCity(item: City) = cityDao.delete(item)

    suspend fun updateCity(item: City) = cityDao.update(item)
}


