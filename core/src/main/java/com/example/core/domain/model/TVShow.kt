package com.example.core.domain.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class TVShow(
    var tvId: Int ?= null,
    var tvPoster: String ?= null,
    var tvTitle: String ?= null,
    var tvRelease: String ?= null,
    var tvSynopsis: String ?= null,
    var tvScore: Double ?= null,
    var tvFavorite: Boolean
): Parcelable
