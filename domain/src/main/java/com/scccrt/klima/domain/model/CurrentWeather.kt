package com.scccrt.klima.domain.model

data class CurrentWeather(
    val coordinates: Coordinates,
    val conditions: List<WeatherCondition>,
    val main: Main,
    val dt: Long,
    val system: WeatherSystem,
    val timezone: Long,
    val cityName: String
)

data class Coordinates(
    val lat: Double,
    val lon: Double
)

data class WeatherCondition(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Double,
    val humidity: Double
)

data class WeatherSystem(
    val id: Int,
    val countryCode: String,
    val sunriseDt: Long,
    val sunsetDt: Long
)