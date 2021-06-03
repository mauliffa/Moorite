package com.example.moorite.di

import com.example.core.domain.usecase.Interactor
import com.example.core.domain.usecase.UseCase
import com.example.moorite.detail.DetailMovieViewModel
import com.example.moorite.detail.DetailTVShowViewModel
import com.example.moorite.movie.MovieViewModel
import com.example.moorite.tvshow.TVShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UseCase> { Interactor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TVShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTVShowViewModel(get()) }
}