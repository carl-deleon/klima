package com.scccrt.klima.ui.navigation.destination

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.scccrt.klima.ui.feature.weather.forecast.WeatherForecastViewModel
import com.scccrt.klima.ui.feature.weather.forecast.WeatherForecastScreen

@Composable
fun WeatherForecastDestination() {
    val viewModel: WeatherForecastViewModel = hiltViewModel()

    WeatherForecastScreen(
        state = viewModel.uiState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.handleEvents(event) }
    )
}