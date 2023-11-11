package com.scccrt.klima.data.repository

import com.scccrt.klima.data.mapper.toWeatherForecast
import com.scccrt.klima.data.remote.api.WeatherApi
import com.scccrt.klima.domain.model.WeatherForecast
import com.scccrt.klima.domain.repository.ForecastRepository
import kotlinx.coroutines.CoroutineDispatcher

class WeatherForecastRepository(
    private val api: WeatherApi,
    dispatcher: CoroutineDispatcher
) : CoroutineRepository(dispatcher), ForecastRepository {

    override suspend fun getWeatherForecast(lat: Double, long: Double): Result<WeatherForecast> =
        safeApiCall {
            api.getWeatherForecast(lat.toString(), long.toString()).toWeatherForecast()
        }
}