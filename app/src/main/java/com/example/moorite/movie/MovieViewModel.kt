package com.example.moorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.UseCase

class MovieViewModel (private val useCase: UseCase) : ViewModel() {
    fun getMovie() : LiveData<Resource<List<Movie>>> = useCase.getMovie().asLiveData()
}