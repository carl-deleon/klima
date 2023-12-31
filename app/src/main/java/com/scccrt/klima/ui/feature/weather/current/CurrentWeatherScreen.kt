package com.scccrt.klima.ui.feature.weather.current

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.scccrt.klima.R
import com.scccrt.klima.domain.model.CurrentWeather
import com.scccrt.klima.ui.base.SIDE_EFFECTS_KEY
import com.scccrt.klima.ui.common.composable.KlimaTopAppBar
import com.scccrt.klima.ui.common.util.CountryUtil.asCountryName
import com.scccrt.klima.ui.common.util.DateUtil.toFormattedDate
import com.scccrt.klima.ui.common.util.DateUtil.toFormattedTime
import com.scccrt.klima.ui.feature.weather.current.composables.WeatherComponent
import com.scccrt.klima.ui.feature.weather.current.composables.WeatherComponentPresentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlin.math.roundToInt

@Composable
fun CurrentWeatherScreen(
    state: CurrentWeatherContract.State,
    effectFlow: Flow<CurrentWeatherContract.Effect>?,
    onEventSent: (event: CurrentWeatherContract.Event) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                CurrentWeatherContract.Effect.LocationDisabled -> {
                    // TODO Show location settings dialog here
                }
            }
        }?.collect()
    }

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
                state.currentWeather != null -> CurrentWeatherSuccessState(currentWeather = state.currentWeather)

                state.isError -> {
                    // TODO error state
                }

                state.isLoading -> {
                    // TODO optional loading state
                }
            }
        }
    }
}

@Composable
fun CurrentWeatherSuccessState(
    currentWeather: CurrentWeather,
    modifier: Modifier = Modifier
) {
    val weatherComponents = listOf(
        WeatherComponentPresentation(
            label = stringResource(id = R.string.humidity),
            value = currentWeather.temperature.humidity.roundToInt().toString(),
            unit = "%",
            iconId = R.drawable.ic_humidity
        ),
        WeatherComponentPresentation(
            label = stringResource(id = R.string.feels_like),
            value = currentWeather.temperature.feelsLike.roundToInt().toString(),
            unit = "°C",
            iconId = R.drawable.ic_temp
        ),
        WeatherComponentPresentation(
            label = stringResource(id = R.string.sunrise),
            value = currentWeather.system.sunriseDt.toFormattedTime(),
            iconId = R.drawable.ic_sunrise
        ),
        WeatherComponentPresentation(
            label = stringResource(id = R.string.sunset),
            value = currentWeather.system.sunsetDt.toFormattedTime(),
            iconId = R.drawable.ic_sunset
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = "${currentWeather.cityName}, ${currentWeather.system.countryCode.asCountryName()}",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = currentWeather.dt.toFormattedDate(),
                    style = MaterialTheme.typography.bodyMedium
                )

                AsyncImage(
                    modifier = Modifier.size(64.dp),
                    model = stringResource(
                        R.string.icon_image_url,
                        currentWeather.conditions.firstOrNull()?.icon ?: "",
                    ),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    error = painterResource(id = R.drawable.ic_placeholder),
                    placeholder = painterResource(id = R.drawable.ic_placeholder),
                )

                Text(
                    text = stringResource(
                        R.string.temperature_value_in_celsius,
                        currentWeather.temperature.temp.roundToInt()
                    ),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                    text = currentWeather.conditions.firstOrNull()?.description ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(Modifier.height(16.dp))
            }
        }

        items(weatherComponents) { weatherComponent ->
            WeatherComponent(weatherComponent = weatherComponent)
        }
    }
}