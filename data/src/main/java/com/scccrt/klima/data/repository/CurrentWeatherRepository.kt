package com.scccrt.klima.data.repository

import com.scccrt.klima.data.mapper.toCurrentWeather
import com.scccrt.klima.data.remote.api.WeatherApi
import com.scccrt.klima.domain.model.CurrentWeather
import com.scccrt.klima.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher

class CurrentWeatherRepository(
    private val api: WeatherApi,
    dispatcher: CoroutineDispatcher
) : CoroutineRepository(dispatcher), WeatherRepository {

    override suspend fun getWeather(lat: Double, long: Double): Result<CurrentWeather> =
        safeApiCall {
            api.getWeather(lat.toString(), long.toString()).toCurrentWeather()
        }
}