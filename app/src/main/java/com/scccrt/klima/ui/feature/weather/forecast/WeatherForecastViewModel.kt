package com.scccrt.klima.ui.feature.weather.forecast

import androidx.lifecycle.viewModelScope
import com.scccrt.klima.domain.repository.ForecastRepository
import com.scccrt.klima.ui.base.BaseViewModel
import com.scccrt.klima.ui.common.location.KlimaLocationManager
import com.scccrt.klima.ui.common.util.DateUtil.toFormattedDate
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
class WeatherForecastViewModel @Inject constructor(
    private val forecastRepository: ForecastRepository,
    locationManager: KlimaLocationManager
) : BaseViewModel<WeatherForecastContract.Event, WeatherForecastContract.State, WeatherForecastContract.Effect>() {

    init {
        val locationCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        locationManager
            .locationUpdates()
            .onEach {
                requestForecast(it.latitude, it.longitude)
                locationCoroutineScope.cancel()
            }
            .catch {
                setState {
                    copy(isError = true)
                }
            }
            .launchIn(locationCoroutineScope)
    }

    private fun requestForecast(lat: Double, long: Double) {
        viewModelScope.launch {
            forecastRepository
                .getWeatherForecast(lat, long)
                .onSuccess {
                    setState {
                        copy(
                            weatherForecast = it.forecastConditions.groupBy { condition ->
                                condition.timestamp.toFormattedDate(
                                    DATE_GROUP_FORMAT
                                )
                            },
                            isLoading = false,
                            isError = false
                        )
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

    override fun setInitialState() = WeatherForecastContract.State(
        weatherForecast = emptyMap(),
        isLoading = false,
        isError = false
    )

    override fun handleEvents(event: WeatherForecastContract.Event) {
        // noop
    }

    companion object {
        private const val DATE_GROUP_FORMAT = "MMM dd"
    }
}