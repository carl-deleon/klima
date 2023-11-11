package com.scccrt.klima.ui.feature.weather.forecast

import com.scccrt.klima.domain.repository.WeatherRepository
import com.scccrt.klima.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel<WeatherForecastContract.Event, WeatherForecastContract.State, WeatherForecastContract.Effect>() {

    override fun setInitialState() = WeatherForecastContract.State(
        weatherForecast = emptyList(),
        isLoading = false,
        isError = false
    )

    override fun handleEvents(event: WeatherForecastContract.Event) {
        // noop
    }
}