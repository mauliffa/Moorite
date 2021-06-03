package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.TVShowEntity
import com.example.core.data.source.local.room.Dao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mDao: Dao) {

    fun getMovie(): Flow<List<MovieEntity>> = mDao.getMovie()

    fun getMovieDetail(movieId: Int): Flow<MovieEntity> =
        mDao.getMovieDetail(movieId)

    suspend fun insertMovie(movies: List<MovieEntity>) = mDao.insertMovie(movies)

    fun getFavoriteMovie():  Flow<List<MovieEntity>> = mDao.getFavoriteMovie()

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.movieFavorite = newState
        mDao.updateMovie(movie)
    }

    fun deleteMovie(movieId: Int){
        mDao.deleteMovie(movieId)
    }

    fun getTV(): Flow<List<TVShowEntity>> = mDao.getTV()

    fun getTVDetail(tvId: Int): Flow<TVShowEntity> =
        mDao.getTVDetail(tvId)

    fun insertTV(tv: List<TVShowEntity>) = mDao.insertTV(tv)

    fun getFavoriteTV(): Flow<List<TVShowEntity>> = mDao.getFavoriteTV()

    fun setFavoriteTV(tv: TVShowEntity, newState: Boolean) {
        tv.tvFavorite = newState
        mDao.updateTV(tv)
    }

    fun deleteTV(tvId: Int){
        mDao.deleteTV(tvId)
    }

}