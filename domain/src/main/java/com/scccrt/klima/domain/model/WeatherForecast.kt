package com.scccrt.klima.domain.model

data class WeatherForecast(
    val city: City,
    val forecastConditions: List<ForecastCondition>
)

data class ForecastCondition(
    val temperature: Temperature,
    val conditions: List<WeatherCondition>,
    val timestamp: Long,
    val displayTimestamp: String
)

data class City(
    val id: Int,
    val name: String,
    val coordinates: Coordinates,
    val countryCode: String
)