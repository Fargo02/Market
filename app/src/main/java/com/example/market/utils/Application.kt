package com.example.market.utils

import android.app.Application
import com.example.market.di.dataModule
import com.example.market.di.interactorModule
import com.example.market.di.repositoryModule
import com.example.market.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule)
        }
    }
}