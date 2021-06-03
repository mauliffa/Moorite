package com.example.core.data.source.remote

import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.data.source.remote.response.TVResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovie(): Flow<ApiResponse<List<MovieResponse>>>{
        return flow {
            try {
                val response = apiService.getListMovie()
                val dataArray = response.resultMovie
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.resultMovie))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTV(): Flow<ApiResponse<List<TVResponse>>>{
        return flow {
            try {
                val response = apiService.getListTV()
                val dataArray = response.resultTV
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.resultTV))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}