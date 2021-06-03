package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TVResponse(
    @field:SerializedName("id")
    val tvId: Int? = null,

    @field:SerializedName("poster_path")
    val tvPoster: String? = null,

    @field:SerializedName("name")
    val tvTitle: String? = null,

    @field:SerializedName("first_air_date")
    val tvRelease: String? = null,

    @field:SerializedName("overview")
    val tvSynopsis: String? = null,

    @field:SerializedName("vote_average")
    val tvScore: Double? = null
)
