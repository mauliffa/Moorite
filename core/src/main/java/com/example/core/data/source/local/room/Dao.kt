package com.example.core.data.source.local.room

import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM movieentities")
    fun getMovie(): Flow<List<com.example.core.data.source.local.entity.MovieEntity>>

    @Query("SELECT * FROM movieentities where movieFavorite = 1")
    fun getFavoriteMovie(): Flow<List<com.example.core.data.source.local.entity.MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovieDetail(movieId: Int): Flow<com.example.core.data.source.local.entity.MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<com.example.core.data.source.local.entity.MovieEntity>)

    @Update
    fun updateMovie(movie: com.example.core.data.source.local.entity.MovieEntity)

    @Query("DELETE FROM movieentities where movieId = :movieId")
    fun deleteMovie (movieId: Int)

    @Query("SELECT * FROM tventities")
    fun getTV(): Flow<List<com.example.core.data.source.local.entity.TVShowEntity>>

    @Query("SELECT * FROM tventities where tvFavorite = 1")
    fun getFavoriteTV(): Flow<List<com.example.core.data.source.local.entity.TVShowEntity>>

    @Transaction
    @Query("SELECT * FROM tventities WHERE tvId = :tvId")
    fun getTVDetail(tvId: Int): Flow<com.example.core.data.source.local.entity.TVShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTV(tv: List<com.example.core.data.source.local.entity.TVShowEntity>)

    @Update
    fun updateTV(tv: com.example.core.data.source.local.entity.TVShowEntity)

    @Query("DELETE FROM tventities where tvId = :tvId")
    fun deleteTV (tvId: Int)
}