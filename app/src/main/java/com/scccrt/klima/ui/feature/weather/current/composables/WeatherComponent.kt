package com.scccrt.klima.ui.feature.weather.current.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scccrt.klima.R

data class WeatherComponentPresentation(
    val label: String,
    val value: String,
    val unit: String = "",
    @DrawableRes val iconId: Int
)

@Composable
fun WeatherComponent(
    weatherComponent: WeatherComponentPresentation,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .padding(8.dp),
        elevation = CardDefaults
            .cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = weatherComponent.label,
                style = MaterialTheme.typography.bodySmall,
            )
            Image(
                painter = painterResource(id = weatherComponent.iconId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(24.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = weatherComponent.value,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = weatherComponent.unit,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@Preview
@Composable
fun WeatherComponentPreview() {
    WeatherComponent(
        WeatherComponentPresentation(
            label = "Humidity",
            value = "67",
            unit = "%",
            iconId = R.drawable.ic_humidity,
        )
    )
}