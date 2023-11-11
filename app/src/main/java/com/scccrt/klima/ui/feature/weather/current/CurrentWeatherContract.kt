package com.scccrt.klima.ui.feature.weather.current

import com.scccrt.klima.domain.model.CurrentWeather
import com.scccrt.klima.ui.base.UiEvent
import com.scccrt.klima.ui.base.UiSideEffect
import com.scccrt.klima.ui.base.UiState

class CurrentWeatherContract {

    sealed class Event : UiEvent

    data class State(
        val currentWeather: CurrentWeather?,
        val isEvening: Boolean,
        val isLoading: Boolean,
        val isError: Boolean
    ) : UiState

    sealed class Effect : UiSideEffect
}