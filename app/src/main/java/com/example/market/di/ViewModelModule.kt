package com.example.market.di

import com.example.market.ui.home.view_model.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel {
        HomeViewModel(get())
    }
}