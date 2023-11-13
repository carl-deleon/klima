package com.scccrt.klima.ui.navigation.destination

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.scccrt.klima.ui.feature.weather.current.CurrentWeatherViewModel
import com.scccrt.klima.ui.feature.weather.current.CurrentWeatherScreen

@Composable
fun CurrentWeatherDestination() {
    val viewModel: CurrentWeatherViewModel = hiltViewModel()

    CurrentWeatherScreen(
        state = viewModel.uiState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.handleEvents(event) }
    )
}