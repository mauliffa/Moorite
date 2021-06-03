package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.entity.TVShowEntity

@Database(entities = [MovieEntity::class, TVShowEntity::class],
    version = 1,
    exportSchema = false)
abstract class MyDatabase: RoomDatabase() {
    abstract fun dao(): Dao
}