package com.bme.weatherapp.ui.details

import androidx.annotation.WorkerThread
import com.bme.weatherapp.persistence.CityDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val cityDao: CityDao
) {

    @WorkerThread
    fun getCityById(id: Long) = flow {
        val city = cityDao.getCity(id)
        emit(city)
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun addFavorite(id: Long) = flow {
        val city = cityDao.getCity(id)
        city.fav = true
        cityDao.update(city)
        emit(city)
    }.flowOn(Dispatchers.IO)
}