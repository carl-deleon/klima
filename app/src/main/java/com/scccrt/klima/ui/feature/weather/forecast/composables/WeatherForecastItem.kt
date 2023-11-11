package com.scccrt.klima.ui.feature.weather.forecast.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.scccrt.klima.R
import com.scccrt.klima.domain.model.ForecastCondition
import com.scccrt.klima.domain.model.Temperature
import com.scccrt.klima.domain.model.WeatherCondition
import com.scccrt.klima.ui.common.util.DateUtil.toFormattedTime
import kotlin.math.roundToInt

@Composable
fun WeatherForecastItem(
    forecastCondition: ForecastCondition,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .padding(8.dp),
        elevation = CardDefaults
            .cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = forecastCondition.timestamp.toFormattedTime(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(2f)
            )

            Text(
                text = stringResource(
                    R.string.temperature_value_in_celsius,
                    forecastCondition.temperature.temp.roundToInt()
                ),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(2f)
            )

            Row(
                modifier = Modifier.weight(4f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier.size(36.dp),
                    model = stringResource(
                        R.string.icon_image_url,
                        forecastCondition.conditions.firstOrNull()?.icon ?: "",
                    ),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    error = painterResource(id = R.drawable.ic_placeholder),
                    placeholder = painterResource(id = R.drawable.ic_placeholder),
                )

                Spacer(modifier = Modifier.size(4.dp))

                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                    text = forecastCondition.conditions.firstOrNull()?.description ?: "",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Composable
fun WeatherForecastHeader(
    label: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier.padding(horizontal = 12.dp, vertical = 4.dp)
    )
}

@Preview
@Composable
fun WeatherForecastItemPreview() {
    WeatherForecastItem(forecastPreview())
}

@Preview
@Composable
fun WeatherForecastHeaderPreview() {
    WeatherForecastHeader(label = "November 01, 2023")
}

fun forecastPreview() = ForecastCondition(
    temperature = Temperature(
        temp = 30.0,
        feelsLike = 30.0,
        tempMin = 28.0,
        tempMax = 36.0,
        pressure = 100.0,
        humidity = 80.0
    ),
    conditions = listOf(
        WeatherCondition(
            id = 0,
            main = "Sunny",
            description = "Sunny",
            icon = "01d"
        )
    ),
    timestamp = 1699722765L,
    displayTimestamp = "2023-11-11 18:00:00"
)