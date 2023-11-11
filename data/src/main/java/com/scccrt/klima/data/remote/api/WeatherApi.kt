package com.scccrt.klima.data.remote.api

import com.scccrt.klima.data.remote.dto.ForecastResult
import com.scccrt.klima.data.remote.dto.WeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

const val DEFAULT_UNIT = "metric"
const val DEFAULT_COUNT = 20

interface WeatherApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = DEFAULT_UNIT
    ): WeatherResult

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = DEFAULT_UNIT,
        @Query("cnt") count: Int = DEFAULT_COUNT
    ): ForecastResult
}