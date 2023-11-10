package com.scccrt.klima.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResult(
    @SerialName("coord") val coordinates: Coordinates,
    @SerialName("weather") val weathers: List<Weather>,
    val main: MainDetails,
    val dt: Long,
    val sys: System,
    val timezone: Long,
    val name: String
)

@Serializable
data class Coordinates(
    val lat: Double,
    @SerialName("lon") val long: Double
)

@Serializable
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class MainDetails(
    val temp: Double,
    @SerialName("feels_like") val feelsLike: Double,
    @SerialName("temp_min") val tempMin: Double,
    @SerialName("temp_max") val tempMax: Double,
    val pressure: Double,
    val humidity: Double
)

@Serializable
data class System(
    val id: Int,
    val country: String,
    val sunrise: String,
    val sunset: String
)