package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.model.TVShow
import com.example.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class Interactor(private val repository: IRepository): UseCase {
    override fun getMovie(): Flow<Resource<List<Movie>>> {
        return repository.getMovie()
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<Movie>> {
        return repository.getDetailMovie(movieId)
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return repository.getFavoriteMovie()
    }

    override fun deleteMovie(movieId: Int) {
        return repository.deleteMovie(movieId)
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return repository.setFavoriteMovie(movie, state)
    }

    override fun getTV(): Flow<Resource<List<TVShow>>> {
        return repository.getTV()
    }

    override fun getDetailTV(tvId: Int): Flow<Resource<TVShow>> {
        return repository.getDetailTV(tvId)
    }

    override fun getFavoriteTV(): Flow<List<TVShow>> {
        return repository.getFavoriteTV()
    }

    override fun deleteTV(tvId: Int) {
        return repository.deleteTV(tvId)
    }

    override fun setFavoriteTV(tv: TVShow, state: Boolean) {
        return repository.setFavoriteTV(tv, state)
    }
}