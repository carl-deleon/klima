package com.scccrt.klima.ui.feature.weather.current

import androidx.lifecycle.viewModelScope
import com.scccrt.klima.domain.repository.WeatherRepository
import com.scccrt.klima.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel<CurrentWeatherContract.Event, CurrentWeatherContract.State, CurrentWeatherContract.Effect>() {

    init {
        viewModelScope.launch {
            
        }
    }

    override fun setInitialState() = CurrentWeatherContract.State(
        null,
        isEvening = false,
        isLoading = false,
        isError = false
    )


    override fun handleEvents(event: CurrentWeatherContract.Event) {

    }
}