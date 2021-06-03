package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getMovie(): Flow<Resource<List<Movie>>>
    fun getDetailMovie(movieId: Int): Flow<Resource<Movie>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun deleteMovie(movieId: Int)
    fun setFavoriteMovie(movie: Movie, state: Boolean)

    fun getTV(): Flow<Resource<List<TVShow>>>
    fun getDetailTV(tvId: Int): Flow<Resource<TVShow>>
    fun getFavoriteTV(): Flow<List<TVShow>>
    fun deleteTV(tvId: Int)
    fun setFavoriteTV(tv: TVShow, state: Boolean)
}