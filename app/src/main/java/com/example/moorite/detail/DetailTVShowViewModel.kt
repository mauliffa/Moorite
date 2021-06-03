package com.example.moorite.detail

import androidx.lifecycle.*
import com.example.core.data.Resource
import com.example.core.domain.model.TVShow
import com.example.core.domain.usecase.UseCase

class DetailTVShowViewModel (private val useCase: UseCase): ViewModel() {
    private val tvId = MutableLiveData<Int>()

    fun setSelectedTV(tvId: Int){
        this.tvId.value = tvId
    }

    var detailTV: LiveData<Resource<TVShow>> = Transformations.switchMap(tvId) { mTVId ->
        useCase.getDetailTV(mTVId).asLiveData()
    }

    fun addAndDeleteFav() {
        val resource = detailTV.value
        if (resource != null) {
            val detail = resource.data
            if (detail != null) {
                val newState = !detail.tvFavorite
                useCase.setFavoriteTV(detail, newState)
            }
        }
    }

}