package com.bme.weatherapp.ui.details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailRepository: DetailsRepository
) : ViewModel() {

    private val cityIdSharedFlow: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)
    private val cityIdSharedFlow2: MutableSharedFlow<Long> = MutableSharedFlow(replay = 1)

    val cityDetailsFlow = cityIdSharedFlow.flatMapLatest {
        detailRepository.getCityById(it)
    }

    val addFavoriteFlow = cityIdSharedFlow2.flatMapLatest {
        detailRepository.addFavorite(it)
    }

    fun loadCityById(id: Long) = cityIdSharedFlow.tryEmit(id)
    fun addFavorite(id: Long) = cityIdSharedFlow2.tryEmit(id)
}