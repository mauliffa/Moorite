package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.TVShowEntity
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.data.source.remote.response.TVResponse
import com.example.core.utils.AppExecutors
import com.example.core.domain.model.Movie
import com.example.core.domain.model.TVShow
import com.example.core.domain.repository.IRepository
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IRepository{

    override fun getMovie(): Flow<Resource<List<Movie>>> =
        object : com.example.core.data.NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            public override fun loadFromDB(): Flow<List<Movie>>{
               return localDataSource.getMovie().map { DataMapper.mapMovieEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.movieId,
                        response.moviePoster,
                        response.movieTitle,
                        response.movieRelease,
                        response.movieSynopsis,
                        response.movieScore
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getDetailMovie(movieId: Int): Flow<Resource<Movie>> {
        return object : com.example.core.data.NetworkBoundResource<Movie, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieDetail(movieId).map { DataMapper.mapDetailMovieEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.movieId,
                        response.moviePoster,
                        response.movieTitle,
                        response.movieRelease,
                        response.movieSynopsis,
                        response.movieScore
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun deleteMovie(movieId: Int) =
        appExecutors.diskIO().execute { localDataSource.deleteMovie(movieId) }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

    override fun getTV(): Flow<Resource<List<TVShow>>> =
        object : com.example.core.data.NetworkBoundResource<List<TVShow>, List<TVResponse>>() {
            public override fun loadFromDB(): Flow<List<TVShow>>{
                return localDataSource.getTV().map { DataMapper.mapTVEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<TVShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TVResponse>>> =
                remoteDataSource.getTV()

            override suspend fun saveCallResult(data: List<TVResponse>) {
                val tvList = ArrayList<TVShowEntity>()
                for (response in data) {
                    val tv = TVShowEntity(
                        response.tvId,
                        response.tvPoster,
                        response.tvTitle,
                        response.tvRelease,
                        response.tvSynopsis,
                        response.tvScore
                    )
                    tvList.add(tv)
                }

                localDataSource.insertTV(tvList)
            }
        }.asFlow()

    override fun getDetailTV(tvId: Int): Flow<Resource<TVShow>>{
        return object : com.example.core.data.NetworkBoundResource<TVShow, List<TVResponse>>() {
            override fun loadFromDB(): Flow<TVShow> {
                return localDataSource.getTVDetail(tvId).map { DataMapper.mapDetailTVEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: TVShow?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<List<TVResponse>>> =
                remoteDataSource.getTV()

            override suspend fun saveCallResult(data: List<TVResponse>) {
                val tvList = ArrayList<TVShowEntity>()
                for (response in data) {
                    val tv = TVShowEntity(
                        response.tvId,
                        response.tvPoster,
                        response.tvTitle,
                        response.tvRelease,
                        response.tvSynopsis,
                        response.tvScore
                    )
                    tvList.add(tv)
                }
                localDataSource.insertTV(tvList)
            }
        }.asFlow()
    }

    override fun getFavoriteTV(): Flow<List<TVShow>> {
        return localDataSource.getFavoriteTV().map { DataMapper.mapTVEntitiesToDomain(it) }
    }

    override fun deleteTV(tvId: Int) {
        appExecutors.diskIO().execute { localDataSource.deleteTV(tvId) }
    }

    override fun setFavoriteTV(tv: TVShow, state: Boolean) {
        val tvEntity = DataMapper.mapTVDomainToEntity(tv)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTV(tvEntity, state) }
    }
}