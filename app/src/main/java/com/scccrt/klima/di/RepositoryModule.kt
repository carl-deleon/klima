package com.scccrt.klima.di

import com.scccrt.klima.data.remote.api.WeatherApi
import com.scccrt.klima.data.repository.CurrentWeatherRepository
import com.scccrt.klima.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

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