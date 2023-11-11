package com.scccrt.klima.ui.feature.weather.current.composables

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
import com.scccrt.klima.ui.feature.weather.current.CurrentWeatherContract
import kotlinx.coroutines.flow.Flow

@Composable
fun CurrentWeatherScreen(
    state: CurrentWeatherContract.State,
    effectFlow: Flow<CurrentWeatherContract.Effect>?,
    onEventSent: (event: CurrentWeatherContract.Event) -> Unit
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