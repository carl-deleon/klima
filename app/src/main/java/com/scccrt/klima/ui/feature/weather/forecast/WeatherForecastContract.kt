package com.scccrt.klima.ui.feature.weather.forecast

import com.scccrt.klima.domain.model.ForecastCondition
import com.scccrt.klima.ui.base.UiEvent
import com.scccrt.klima.ui.base.UiSideEffect
import com.scccrt.klima.ui.base.UiState

class WeatherForecastContract {

    sealed class Event : UiEvent

    data class State(
        val weatherForecast: Map<String, List<ForecastCondition>>,
        val isLoading: Boolean,
        val isError: Boolean
    ) : UiState

    sealed class Effect : UiSideEffect
}