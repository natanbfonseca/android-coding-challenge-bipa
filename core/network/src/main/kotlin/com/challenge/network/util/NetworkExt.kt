package com.challenge.network.util

import com.challenge.common.error.RemoteError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<T> =
    flow {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(body)
            } else throw RemoteError.Unknown
        } else {
            val errorMessage = response.errorBody()?.string() ?: RemoteError.Unknown.message
            throw RemoteError.ApiError(
                response.code(),
                errorMessage,
                responseToError(response.code())
            )
        }
        emitAll(emptyFlow())
    }
        .catch { throwable ->
            val error = when (throwable) {
                is SocketTimeoutException -> RemoteError.RequestTimeout
                is UnknownHostException -> RemoteError.NoInternet
                is RemoteError -> throwable
                else -> {
                    throwable.printStackTrace()
                    RemoteError.Unknown
                }
            }
            throw error
        }

private fun responseToError(code: Int): Throwable {
    return when (code) {
        400 -> RemoteError.Unknown
        401, 403 -> RemoteError.Unauthorized
        404 -> RemoteError.NotFound
        408 -> RemoteError.RequestTimeout
        429 -> RemoteError.TooManyRequests
        in 500..599 -> RemoteError.Server
        else -> RemoteError.Unknown
    }
}
