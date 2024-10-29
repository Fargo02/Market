package com.example.market.di

import com.example.market.data.search.LocationSearchRepositoryImpl
import com.example.market.domain.search.LocationSearchRepository
import org.koin.dsl.module


val repositoryModule = module {

    single <LocationSearchRepository>{
        LocationSearchRepositoryImpl(get(), get())
    }


}