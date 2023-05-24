package com.bme.weatherapp.ui.about

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val aboutRepository: AboutRepository
) : ViewModel() {

}