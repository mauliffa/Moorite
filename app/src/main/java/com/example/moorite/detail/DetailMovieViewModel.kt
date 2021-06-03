package com.example.moorite.detail

import androidx.lifecycle.*
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.UseCase

class DetailMovieViewModel (private val useCase: UseCase): ViewModel() {
    private val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int){
        this.movieId.value = movieId
    }

    var detailMovie: LiveData<Resource<Movie>> = Transformations.switchMap(movieId) { mMovieId ->
        useCase.getDetailMovie(mMovieId).asLiveData()
    }

    fun addAndDeleteFav() {
        val resource = detailMovie.value
        if (resource != null) {
            val detail = resource.data
            if (detail != null) {
                val newState = !detail.movieFavorite
                useCase.setFavoriteMovie(detail, newState)
            }
         }
    }

}