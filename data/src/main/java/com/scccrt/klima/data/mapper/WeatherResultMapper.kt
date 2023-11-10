package com.scccrt.klima.data.mapper

import com.scccrt.klima.data.remote.dto.MainDetails
import com.scccrt.klima.data.remote.dto.System
import com.scccrt.klima.data.remote.dto.Weather
import com.scccrt.klima.data.remote.dto.WeatherResult
import com.scccrt.klima.domain.model.Coordinates
import com.scccrt.klima.domain.model.CurrentWeather
import com.scccrt.klima.domain.model.Main
import com.scccrt.klima.domain.model.WeatherCondition
import com.scccrt.klima.domain.model.WeatherSystem

fun WeatherResult.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        coordinates = Coordinates(coordinates.lat, coordinates.long),
        conditions = weathers.map { it.toWeatherCondition() },
        main = main.toMain(),
        dt = dt,
        system = sys.toWeatherSystem(),
        timezone = timezone,
        cityName = name
    )
}

fun Weather.toWeatherCondition() = WeatherCondition(id, main, description, icon)

fun MainDetails.toMain() = Main(temp, feelsLike, tempMin, tempMax, pressure, humidity)

fun System.toWeatherSystem() = WeatherSystem(id, countryCode = country, sunriseDt = sunrise, sunsetDt = sunset)