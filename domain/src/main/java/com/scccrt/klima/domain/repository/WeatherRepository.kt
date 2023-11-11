package com.scccrt.klima.domain.repository

import com.scccrt.klima.domain.model.CurrentWeather

interface WeatherRepository {

    suspend fun getWeather(lat: Double, long: Double): Result<CurrentWeather>
}