package com.challenge.common.error

sealed class Error(override val message: String? = null, override val cause: Throwable? = null) :
    Throwable(
        message = message,
        cause = cause
    )