package com.example.market.di

import com.example.market.data.mapper.LocationResponseMapper
import com.example.market.data.search.NetworkClient
import com.example.market.data.search.network.DaDataApi
import com.example.market.data.search.network.RetrofitNetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {

    single<DaDataApi> {
        Retrofit.Builder()
            .baseUrl("https://suggestions.dadata.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DaDataApi::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), androidContext())
    }

    single { LocationResponseMapper() }
}