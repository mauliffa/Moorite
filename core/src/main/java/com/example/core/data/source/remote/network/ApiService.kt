package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.ListMovieResponse
import com.example.core.data.source.remote.response.ListTVResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie?api_key=dce33a03456fa4d305b99e7e555d558f")
    suspend fun getListMovie(): ListMovieResponse

    @GET("tv?api_key=dce33a03456fa4d305b99e7e555d558f")
    suspend fun getListTV(): ListTVResponse
}