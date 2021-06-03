package com.example.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.Movie
import com.example.core.domain.model.TVShow
import com.example.core.domain.usecase.UseCase

class FavoriteViewModel (private val useCase: UseCase) : ViewModel(){
    fun getFavoriteMovie(): LiveData<List<Movie>> = useCase.getFavoriteMovie().asLiveData()
    fun getFavoriteTV(): LiveData<List<TVShow>> = useCase.getFavoriteTV().asLiveData()
}