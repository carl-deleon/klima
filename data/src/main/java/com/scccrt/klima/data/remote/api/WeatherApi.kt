package com.scccrt.klima.data.remote.api

import com.scccrt.klima.data.remote.dto.WeatherResult
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeather(@Query("lat") lat: String, @Query("lon") lon: String): WeatherResult
}