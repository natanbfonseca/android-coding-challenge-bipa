package com.challenge.home.di

import com.challenge.home.ui.BipaViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object FeatureHomeModule {
    val getModule = module {
        viewModelOf(::BipaViewModel)
    }
}