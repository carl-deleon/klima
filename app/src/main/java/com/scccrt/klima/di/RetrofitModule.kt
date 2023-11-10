package com.scccrt.klima.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.scccrt.klima.BuildConfig
import com.scccrt.klima.data.remote.interceptor.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val CONTENT_TYPE = "application/json"

    @Provides
    @Singleton
    fun defaultCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun retrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(converterFactory())
            .client(okHttpClient())
            .build()

    private fun converterFactory(): Converter.Factory {
        val jsonSerializer = Json { ignoreUnknownKeys = true }
        val contentType = CONTENT_TYPE.toMediaType()

        return jsonSerializer.asConverterFactory(contentType)
    }

    private fun okHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .apply { clientInterceptors().forEach { addInterceptor(it) } }
            .build()

    private fun clientInterceptors() = listOf(httpLoggingInterceptor(), apiKeyInterceptor())

    private fun apiKeyInterceptor() = ApiKeyInterceptor()

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }
            .apply { level = HttpLoggingInterceptor.Level.BODY }
}