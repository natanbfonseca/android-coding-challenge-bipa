package com.challenge.domain.di

import com.challenge.domain.usecase.GetNodesUseCase
import com.challenge.domain.usecase.GetNodesUseCaseImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object UseCaseModule {
    val getModule = module {
        singleOf(::GetNodesUseCaseImpl).bind<GetNodesUseCase>()
    }
}