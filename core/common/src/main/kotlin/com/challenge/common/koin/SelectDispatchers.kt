package com.challenge.common.koin

sealed interface SelectDispatchers {

    data object IO : SelectDispatchers {
        const val NAMED = "IODispatcher"
    }

    data object Main : SelectDispatchers {
        const val NAMED = "MainDispatcher"
    }

    data object Default : SelectDispatchers {
        const val NAMED = "DefaultDispatcher"
    }

    data object Unconfined : SelectDispatchers {
        const val NAMED = "UnconfinedDispatcher"
    }
}