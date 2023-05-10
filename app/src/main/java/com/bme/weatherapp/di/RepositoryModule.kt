package com.bme.weatherapp.di

import com.bme.weatherapp.network.WeatherService
import com.bme.weatherapp.persistence.CityDao
import com.bme.weatherapp.ui.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        weatherService: WeatherService,
        cityDao: CityDao
    ): MainRepository {
        return MainRepository(weatherService, cityDao)
    }
}