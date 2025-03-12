package com.challenge.common.error

sealed class RemoteError(override val message: String) : Error() {
    data object NoInternet : RemoteError(message = "No internet connection") {
        private fun readResolve(): Any = NoInternet
    }

    data object RequestTimeout : RemoteError(message = "Request timeout") {
        private fun readResolve(): Any = RequestTimeout
    }

    data object Server : RemoteError(message = "Server error") {
        private fun readResolve(): Any = Server
    }

    data object TooManyRequests : RemoteError(message = "Too many requests") {
        private fun readResolve(): Any = TooManyRequests
    }

    data object Serialization : RemoteError(message = "Serialization error") {
        private fun readResolve(): Any = Serialization
    }

    data object Unauthorized : RemoteError(message = "Unauthorized") {
        private fun readResolve(): Any = Unauthorized
    }

    data object NotFound : RemoteError(message = "Not Found") {
        private fun readResolve(): Any = NotFound
    }

    data object Unknown : RemoteError(message = "Unknown error") {
        private fun readResolve(): Any = Unknown
    }

    data class ApiError(
        val code: Int,
        override val message: String,
        override val cause: Throwable? = null
    ) : RemoteError("API error: $code - $message")
}