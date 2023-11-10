package com.scccrt.klima.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

open class CoroutineRepository(private val dispatcher: CoroutineDispatcher) {

    suspend fun <T> safeApiCall(call: suspend () -> T): Result<T> = runCatching {
        withContext(dispatcher) {
            call.invoke()
        }
    }
}