package com.scccrt.klima.domain.repository

import com.scccrt.klima.domain.model.WeatherForecast

interface ForecastRepository {

    suspend fun getWeatherForecast(lat: Double, long: Double): Result<WeatherForecast>
}