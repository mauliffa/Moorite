package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="movieId")
    var movieId: Int ?= null,

    @ColumnInfo(name="moviePoster")
    var moviePoster: String ?= null,

    @ColumnInfo(name="movieTitle")
    var movieTitle: String ?= null,

    @ColumnInfo(name="movieRelease")
    var movieRelease: String ?= null,

    @ColumnInfo(name="movieSynopsis")
    var movieSynopsis: String ?= null,

    @ColumnInfo(name="movieScore")
    var movieScore: Double ?= null,

    @ColumnInfo(name = "movieFavorite")
    var movieFavorite: Boolean = false,
)
