package com.scccrt.klima.ui.feature.weather.forecast

import androidx.lifecycle.viewModelScope
import com.scccrt.klima.domain.repository.ForecastRepository
import com.scccrt.klima.ui.base.BaseViewModel
import com.scccrt.klima.ui.common.util.DateUtil.toFormattedDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val forecastRepository: ForecastRepository
) : BaseViewModel<WeatherForecastContract.Event, WeatherForecastContract.State, WeatherForecastContract.Effect>() {

    init {
        viewModelScope.launch {
            forecastRepository
                .getWeatherForecast(14.6542974, 121.0244606)
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