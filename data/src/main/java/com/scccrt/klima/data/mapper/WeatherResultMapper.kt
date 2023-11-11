package com.scccrt.klima.data.mapper

import com.scccrt.klima.data.remote.dto.System
import com.scccrt.klima.data.remote.dto.WeatherResult
import com.scccrt.klima.data.remote.dto.WeatherTemperature
import com.scccrt.klima.domain.model.Coordinates
import com.scccrt.klima.domain.model.CurrentWeather
import com.scccrt.klima.domain.model.Temperature
import com.scccrt.klima.domain.model.WeatherSystem

fun WeatherResult.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        coordinates = Coordinates(coordinates.lat, coordinates.long),
        conditions = weatherConditions.map { it.toWeatherCondition() },
        temperature = main.toMain(),
        dt = dt,
        system = sys.toWeatherSystem(),
        timezone = timezone,
        cityName = name
    )
}

fun DataWeatherCondition.toWeatherCondition() = DomainWeatherCondition(id, main, description, icon)

fun WeatherTemperature.toMain() = Temperature(temp, feelsLike, tempMin, tempMax, pressure, humidity)

fun System.toWeatherSystem() = WeatherSystem(
    id,
    countryCode = country,
    sunriseDt = sunrise,
    sunsetDt = sunset
)