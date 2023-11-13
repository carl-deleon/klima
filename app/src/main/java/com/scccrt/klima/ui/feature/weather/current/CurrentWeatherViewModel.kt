package com.scccrt.klima.ui.feature.weather.current

import androidx.lifecycle.viewModelScope
import com.scccrt.klima.domain.repository.WeatherRepository
import com.scccrt.klima.ui.base.BaseViewModel
import com.scccrt.klima.ui.common.location.KlimaLocationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationManager: KlimaLocationManager
) : BaseViewModel<CurrentWeatherContract.Event, CurrentWeatherContract.State, CurrentWeatherContract.Effect>() {

    private val locationCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        getWeather()
    }

    private fun getWeather() {
        locationManager
            .locationUpdates()
            .onEach {
                requestCurrentWeather(it.latitude, it.longitude)
                locationCoroutineScope.cancel()
            }
            .catch {
                setEffect { CurrentWeatherContract.Effect.LocationDisabled }
                Timber.e(it)
            }
            .launchIn(locationCoroutineScope)
    }

    private fun requestCurrentWeather(lat: Double, long: Double) {
        viewModelScope.launch {
            weatherRepository
                .getWeather(lat, long)
                .onSuccess {
                    setState {
                        copy(currentWeather = it, isLoading = false, isError = false)
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
        isLoading = false,
        isError = false
    )

    override fun handleEvents(event: CurrentWeatherContract.Event) {
        // noop
        when (event) {
            CurrentWeatherContract.Event.GetWeather -> getWeather()
        }
    }
}