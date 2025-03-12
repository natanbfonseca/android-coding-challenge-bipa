package com.challenge.bipa

import android.app.Application
import com.challenge.common.koin.DispatchersModule
import com.challenge.data.di.RepositoryModule
import com.challenge.domain.di.UseCaseModule
import com.challenge.home.di.FeatureHomeModule
import com.challenge.network.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BipaApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BipaApplication)
            modules(getAllModules())
        }
    }
}

private fun getAllModules() = listOf(
    NetworkModule.getModule(BuildConfig.BASE_URL, BuildConfig.DEBUG),
    DispatchersModule.getModule,
    RepositoryModule.getModule,
    UseCaseModule.getModule,
    FeatureHomeModule.getModule
)