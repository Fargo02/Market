package com.example.market.di

import com.example.market.domain.search.LocationSearchInteractor
import com.example.market.domain.search.impl.LocationSearchInteractorImpl
import org.koin.dsl.module


val interactorModule = module {

    single<LocationSearchInteractor>{
        LocationSearchInteractorImpl(get())
    }
}