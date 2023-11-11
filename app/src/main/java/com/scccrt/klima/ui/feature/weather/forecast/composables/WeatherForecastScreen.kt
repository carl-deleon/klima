package com.scccrt.klima.ui.feature.weather.forecast.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
        }
    }
}