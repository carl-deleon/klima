package com.scccrt.klima.di

import com.scccrt.klima.data.remote.api.WeatherApi
import com.scccrt.klima.data.repository.CurrentWeatherRepository
import com.scccrt.klima.data.repository.WeatherForecastRepository
import com.scccrt.klima.domain.repository.ForecastRepository
import com.scccrt.klima.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideCurrentWeatherRepository(
        api: WeatherApi,
        dispatcher: CoroutineDispatcher
    ): WeatherRepository = CurrentWeatherRepository(api, dispatcher)
}

@Module
@InstallIn(SingletonComponent::class)
object ForecastRepositoryModule {

    @Provides
    @Singleton
    fun provideForecastRepository(
        api: WeatherApi,
        dispatcher: CoroutineDispatcher
    ): ForecastRepository = WeatherForecastRepository(api, dispatcher)
}