package com.challenge.data.di

import com.challenge.data.repository.BipaRepository
import com.challenge.data.repository.BipaRepositoryImpl
import com.challenge.common.koin.SelectDispatchers
import com.challenge.data.util.NetworkMonitor
import com.challenge.data.util.NetworkMonitorImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

object RepositoryModule {
    val getModule = module {
        single {
            BipaRepositoryImpl(
                remote = get(),
                ioDispatcher = get(named(SelectDispatchers.IO.NAMED))
            )
        }.bind<BipaRepository>()

        single {
            NetworkMonitorImpl(
                context = androidContext(),
                ioDispatcher = get(named(SelectDispatchers.IO.NAMED))
            )
        }.bind<NetworkMonitor>()
    }
}