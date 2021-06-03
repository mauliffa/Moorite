package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTVResponse (
    @field:SerializedName("page")
    val pageTV: Int,

    @field:SerializedName("results")
    val resultTV: List<TVResponse>
)