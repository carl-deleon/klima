package com.scccrt.klima.data.remote.interceptor

import com.scccrt.klima.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
            .let {
                val newUrl = it.url
                    .newBuilder()
                    .addQueryParameter(APP_ID, BuildConfig.OW_API_KEY)
                    .build()

                it.newBuilder().url(newUrl).build()
            }

        return chain.proceed(newRequest)
    }

    companion object {
        private const val APP_ID = "appid"
    }
}