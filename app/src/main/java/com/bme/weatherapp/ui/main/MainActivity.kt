package com.bme.weatherapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.VisibleForTesting
import com.bme.weatherapp.persistence.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}