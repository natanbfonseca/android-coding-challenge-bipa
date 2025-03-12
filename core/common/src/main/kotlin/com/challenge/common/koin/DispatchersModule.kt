package com.challenge.common.koin

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

object DispatchersModule {
    val getModule = module {
        single(named(SelectDispatchers.IO.NAMED)) {
            Dispatchers.IO
        }
        single(named(SelectDispatchers.Main.NAMED)) {
            Dispatchers.Main
        }
        single(named(SelectDispatchers.Default.NAMED)) {
            Dispatchers.Default
        }
        single(named(SelectDispatchers.Unconfined.NAMED)) {
            Dispatchers.Unconfined
        }
    }
}

