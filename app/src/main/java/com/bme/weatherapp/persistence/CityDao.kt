package com.bme.weatherapp.persistence

import androidx.room.*
import com.bme.weatherapp.model.City
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query("SELECT * from cities ORDER BY cityName ASC")
    fun getAllCities(): Flow<List<City>>

    @Query("SELECT * from cities WHERE fav == 1 ORDER BY cityName ASC")
    fun getAllFavoriteCities(): Flow<List<City>>

    @Query("SELECT * from cities WHERE id = :id")
    fun getCity(id: Int): Flow<City>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: City)

    @Update
    suspend fun update(city: City)

    @Delete
    suspend fun delete(city: City)
}