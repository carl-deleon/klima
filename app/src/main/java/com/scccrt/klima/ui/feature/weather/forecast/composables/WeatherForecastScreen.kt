package com.scccrt.klima.ui.feature.weather.forecast.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.scccrt.klima.domain.model.ForecastCondition
import com.scccrt.klima.ui.common.KlimaTopAppBar
import com.scccrt.klima.ui.feature.weather.forecast.WeatherForecastContract
import kotlinx.coroutines.flow.Flow

@Composable
fun WeatherForecastScreen(
    state: WeatherForecastContract.State,
    effectFlow: Flow<WeatherForecastContract.Effect>?,
    onEventSent: (event: WeatherForecastContract.Event) -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = { KlimaTopAppBar() }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                state.weatherForecast.isNotEmpty() -> WeatherForecastSuccessState(state.weatherForecast)
                state.isError -> {

                }

                state.isLoading -> {

                }
            }
        }
    }
}

@Composable
fun WeatherForecastSuccessState(
    forecastConditions: Map<String, List<ForecastCondition>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {

        forecastConditions.forEach { forecastCondition ->
            item {
                WeatherForecastHeader(label = forecastCondition.key)
            }

            items(forecastCondition.value) { condition ->
                WeatherForecastItem(forecastCondition = condition, modifier = modifier)
            }
        }
    }
}