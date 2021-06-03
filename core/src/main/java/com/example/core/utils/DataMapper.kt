package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.TVShowEntity
import com.example.core.domain.model.Movie
import com.example.core.domain.model.TVShow

object DataMapper {
    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                moviePoster = it.moviePoster,
                movieTitle = it.movieTitle,
                movieRelease = it.movieRelease,
                movieScore = it.movieScore,
                movieSynopsis = it.movieSynopsis,
                movieFavorite = false
            )
        }

    fun mapDetailMovieEntitiesToDomain(input: MovieEntity) = Movie(
        movieId = input.movieId,
        moviePoster = input.moviePoster,
        movieTitle = input.movieTitle,
        movieRelease = input.movieRelease,
        movieSynopsis = input.movieSynopsis,
        movieScore = input.movieScore,
        movieFavorite = input.movieFavorite
    )

    fun mapMovieDomainToEntity(input: Movie) = MovieEntity(
            movieId = input.movieId,
            moviePoster = input.moviePoster,
            movieTitle = input.movieTitle,
            movieRelease = input.movieRelease,
            movieScore = input.movieScore,
            movieSynopsis = input.movieSynopsis,
            movieFavorite = input.movieFavorite
        )

    fun mapTVEntitiesToDomain(input: List<TVShowEntity>): List<TVShow> =
        input.map {
            TVShow(
                tvId = it.tvId,
                tvPoster = it.tvPoster,
                tvTitle = it.tvTitle,
                tvRelease = it.tvRelease,
                tvScore = it.tvScore,
                tvSynopsis = it.tvSynopsis,
                tvFavorite = false
            )
        }

    fun mapDetailTVEntitiesToDomain(input: TVShowEntity) = TVShow(
        tvId = input.tvId,
        tvPoster = input.tvPoster,
        tvTitle = input.tvTitle,
        tvRelease = input.tvRelease,
        tvSynopsis = input.tvSynopsis,
        tvScore = input.tvScore,
        tvFavorite = input.tvFavorite
    )

    fun mapTVDomainToEntity(input: TVShow) = TVShowEntity(
        tvId = input.tvId,
        tvPoster = input.tvPoster,
        tvTitle = input.tvTitle,
        tvRelease = input.tvRelease,
        tvScore = input.tvScore,
        tvSynopsis = input.tvSynopsis,
        tvFavorite = input.tvFavorite
    )
}