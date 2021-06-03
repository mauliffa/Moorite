package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    val movieId: Int? = null,

    @field:SerializedName("poster_path")
    val moviePoster: String? = null,

    @field:SerializedName("title")
    val movieTitle: String? = null,

    @field:SerializedName("release_date")
    val movieRelease: String? = null,

    @field:SerializedName("overview")
    val movieSynopsis: String? = null,

    @field:SerializedName("vote_average")
    val movieScore: Double? = null
)
