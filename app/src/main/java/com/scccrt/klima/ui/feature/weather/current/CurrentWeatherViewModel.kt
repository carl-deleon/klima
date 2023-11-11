package com.scccrt.klima.ui.feature.weather.current

import androidx.lifecycle.viewModelScope
import com.scccrt.klima.domain.repository.WeatherRepository
import com.scccrt.klima.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel<CurrentWeatherContract.Event, CurrentWeatherContract.State, CurrentWeatherContract.Effect>() {

    init {
        viewModelScope.launch {
            weatherRepository
                .getWeather(14.6542974, 121.0244606)
                .onSuccess {
                    setState {
                        copy(currentWeather = it, isLoading = false, isError = false, isEvening = false)
                    }
                }
                .onFailure {
                    Timber.e(it)
                    setState {
                        copy(isError = true)
                    }
                }
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