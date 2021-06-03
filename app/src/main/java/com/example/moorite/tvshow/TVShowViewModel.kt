package com.example.moorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.data.Resource
import com.example.core.domain.model.TVShow
import com.example.core.domain.usecase.UseCase

class TVShowViewModel (private val useCase: UseCase) : ViewModel() {
    fun getTV(): LiveData<Resource<List<TVShow>>> = useCase.getTV().asLiveData()
}