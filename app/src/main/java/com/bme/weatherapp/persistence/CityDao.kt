package com.bme.weatherapp.persistence

import androidx.room.*
import com.bme.weatherapp.model.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("SELECT * from cities ORDER BY (fav == 1) DESC, cityName ASC")
    fun getAllCities(): List<City>

    @Query("SELECT * from cities WHERE fav == 1 ORDER BY cityName ASC")
    fun getAllFavoriteCities(): List<City>

    @Query("SELECT * from cities WHERE id = :id")
    fun getCity(id: Long): City

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: City)

    @Update
    suspend fun update(city: City)

    @Delete
    suspend fun delete(city: City)
}