package com.example.core.domain.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Movie(
    var movieId: Int ?= null,
    var moviePoster: String ?= null,
    var movieTitle: String ?= null,
    var movieRelease: String ?= null,
    var movieSynopsis: String ?= null,
    var movieScore: Double ?= null,
    var movieFavorite: Boolean
): Parcelable
