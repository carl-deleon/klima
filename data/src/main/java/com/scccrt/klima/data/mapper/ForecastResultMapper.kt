package com.scccrt.klima.data.mapper

import com.scccrt.klima.data.remote.dto.ForecastCity
import com.scccrt.klima.data.remote.dto.ForecastResult
import com.scccrt.klima.data.remote.dto.ForecastWeather
import com.scccrt.klima.domain.model.City
import com.scccrt.klima.domain.model.Coordinates
import com.scccrt.klima.domain.model.ForecastCondition
import com.scccrt.klima.domain.model.WeatherForecast

fun ForecastResult.toWeatherForecast() = WeatherForecast(
    city = city.toCity(),
    forecastConditions = forecast.map { it.toForecastCondition() }
)

fun ForecastCity.toCity() = City(
    id = id,
    name = name,
    coordinates = Coordinates(coordinates.lat, coordinates.long),
    countryCode = countryCode
)

fun ForecastWeather.toForecastCondition() = ForecastCondition(
    temperature = main.toMain(),
    conditions = condition.map { it.toWeatherCondition() },
    timestamp = dateTime,
    displayTimestamp = displayTime
)