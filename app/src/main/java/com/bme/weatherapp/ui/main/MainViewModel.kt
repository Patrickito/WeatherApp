package com.bme.weatherapp.ui.main

import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bme.weatherapp.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainRepository: MainRepository
) : ViewModel() {
    val cityList: Flow<List<City>> = mainRepository.getAllCitiesStream()

    private val cityIdSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)

    val addfavoriteFlow = cityIdSharedFlow.flatMapLatest {
        mainRepository.addFavorite(it)
    }

    fun addFavorite(id: Long) = cityIdSharedFlow.tryEmit(id)

    private val _selectedTab: MutableState<Int> = mutableStateOf(0)
    val selectedTab: State<Int> get() = _selectedTab

    val location: Pair<Double, Double>? = mainRepository.getLocation()



    fun selectTab(@StringRes tab: Int) {
        _selectedTab.value = tab
    }
}