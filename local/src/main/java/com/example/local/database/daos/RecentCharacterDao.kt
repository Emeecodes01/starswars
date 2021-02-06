package com.example.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.local.models.CharacterLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RecentCharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun saveRecentCharacter(characterLocalModel: CharacterLocalModel): Long

    @Query("SELECT * FROM recent_characters ORDER BY timeFetched DESC")
    abstract fun getAllRecentCharacters(): Flow<List<CharacterLocalModel>>

    @Query("DELETE FROM recent_characters WHERE name =:name")
    abstract suspend fun deleteCharacterWithName(name: String)
}