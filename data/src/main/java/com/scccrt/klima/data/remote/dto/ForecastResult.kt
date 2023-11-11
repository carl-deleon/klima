package com.scccrt.klima.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResult(
    val city: ForecastCity,
    @SerialName("list") val forecast: List<ForecastWeather>
)

@Serializable
data class ForecastCity(
    val id: Int,
    val name: String,
    @SerialName("coord") val coordinates: Coordinates,
    @SerialName("country") val countryCode: String
)

@Serializable
data class ForecastWeather(
    val main: WeatherTemperature,
    @SerialName("weather") val condition: List<WeatherCondition>,
    @SerialName("dt") val dateTime: Long,
    @SerialName("dt_txt") val displayTime: String
)