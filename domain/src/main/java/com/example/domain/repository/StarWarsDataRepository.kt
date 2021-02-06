package com.example.domain.repository

import com.example.domain.models.Character
import com.example.domain.models.Film
import com.example.domain.models.HomeWorld
import com.example.domain.models.Species
import kotlinx.coroutines.flow.Flow

interface StarWarsDataRepository {
    suspend fun searchCharacter(name: String): List<Character>
    suspend fun saveRecent(character: Character)
    suspend fun getSpecies(speciesUrl: List<String>): List<Species>
    suspend fun getFilms(filmsUrl: List<String>): List<Film>
    suspend fun deleteCharacter(name: String)

    /**
     * This uses a URL to load more items based on the search parameter given
     */
    suspend fun loadMoreCharacters(): List<Character>
    fun getRecents(): Flow<List<Character>>

    /**
     * Saves the next URL used for pagination
     */
    var next: String?
}