package com.scccrt.klima.ui.feature.weather.current

import com.scccrt.klima.domain.model.CurrentWeather
import com.scccrt.klima.ui.base.UiEvent
import com.scccrt.klima.ui.base.UiSideEffect
import com.scccrt.klima.ui.base.UiState

class CurrentWeatherContract {

    sealed class Event : UiEvent {
        data object GetWeather : Event()
    }

    data class State(
        val currentWeather: CurrentWeather?,
        val isLoading: Boolean,
        val isError: Boolean
    ) : UiState

    sealed class Effect : UiSideEffect {
        data object LocationDisabled : Effect()
    }
}