package com.example.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favModule = module{
    viewModel { FavoriteViewModel(get()) }
}