package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @field:SerializedName("page")
    val pageMovie: Int,

    @field:SerializedName("results")
    val resultMovie: List<MovieResponse>
)
