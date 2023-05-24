package com.bme.weatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.ktor.server.application.hooks.CallFailed.install
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.get
import io.ktor.server.routing.post

@HiltAndroidApp
class WeatherApp : Application() {

}
