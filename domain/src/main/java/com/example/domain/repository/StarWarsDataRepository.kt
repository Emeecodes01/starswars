package com.example.domain.repository

import com.example.domain.models.Character

interface StarWarsDataRepository {
    suspend fun searchCharacter(name: String): List<Character>
    suspend fun saveRecent(character: Character)
}