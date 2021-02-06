package com.example.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.local.database.converters.StringListConverter
import com.example.local.database.daos.RecentCharacterDao
import com.example.local.models.CharacterLocalModel
import com.example.local.utils.StarWarsLocalConstants


@Database(
    entities = [CharacterLocalModel::class],
    version = StarWarsLocalConstants.DB_VERSION,
    exportSchema = false
)
@TypeConverters(
    StringListConverter::class
)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract fun recentDao(): RecentCharacterDao
}