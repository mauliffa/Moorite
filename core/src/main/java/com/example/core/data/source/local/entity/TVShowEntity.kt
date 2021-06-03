package com.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tventities")
data class TVShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: Int ?= null,

    @ColumnInfo(name = "tvPoster")
    var tvPoster: String ?= null,

    @ColumnInfo(name = "tvTitle")
    var tvTitle: String ?= null,

    @ColumnInfo(name = "tvRelease")
    var tvRelease: String ?= null,

    @ColumnInfo(name = "tvSynopsis")
    var tvSynopsis: String ?= null,

    @ColumnInfo(name = "tvScore")
    var tvScore: Double ?= null,

    @ColumnInfo(name = "tvFavorite")
    var tvFavorite: Boolean = false,
)
