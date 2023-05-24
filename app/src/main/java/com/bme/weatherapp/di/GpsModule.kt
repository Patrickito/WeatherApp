package com.bme.weatherapp.di

import android.content.Context
import android.location.LocationManager
import com.bme.weatherapp.gps.LocationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GpsModule {
    @Provides
    @Singleton
    fun provideLocationManager(context: Context): LocationManager {
        return context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }


    @Provides
    @Singleton
    fun provideLocationService(locationManager: LocationManager, context: Context): LocationService {
        return LocationService(locationManager, context);
    }
}