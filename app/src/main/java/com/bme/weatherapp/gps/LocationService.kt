package com.bme.weatherapp.gps

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bme.weatherapp.network.WeatherService
import com.bme.weatherapp.persistence.CityDao
import javax.inject.Inject

class LocationService @Inject constructor(
    private val locationManager: LocationManager,
    private val context : Context
) {

    fun getLonLat(): Pair<Double, Double>? {

        val lastKnownLocation = if (ContextCompat.checkSelfPermission(
                context,
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        } else {
            null
        }


        return if (lastKnownLocation != null) {
            Pair(lastKnownLocation.latitude, lastKnownLocation.longitude)
        } else {
            null
        }
    }

}