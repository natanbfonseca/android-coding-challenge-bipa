package com.challenge.network.di

import com.challenge.network.data.RemoteDataSource
import com.challenge.network.data.RemoteDataSourceImpl
import com.challenge.network.service.LightningService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    fun getModule(baseUrl: String, isDebug: Boolean) = module {

        single { provideOkHttpClient(isDebug = isDebug) }.bind<OkHttpClient>()

        single {
            provideRetrofit(
                baseUrl = baseUrl,
                okHttpClient = get()
            )
        }.bind<Retrofit>()

        singleOf(::provideLighting).bind<LightningService>()

        singleOf(::RemoteDataSourceImpl).bind<RemoteDataSource>()

    }

    private fun provideOkHttpClient(isDebug: Boolean): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (isDebug) {
                    level = (HttpLoggingInterceptor.Level.BODY)
                }
            })
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun provideLighting(retrofit: Retrofit): LightningService {
        return retrofit.create(LightningService::class.java)
    }
}