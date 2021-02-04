package com.example.domain.repository

import com.example.domain.models.Character
import com.example.domain.models.Film
import com.example.domain.models.HomeWorld
import com.example.domain.models.Species

interface StarWarsDataRepository {
    suspend fun searchCharacter(name: String): List<Character>
    suspend fun saveRecent(character: Character)
    suspend fun getSpecies(speciesUrl: List<String>): List<Species>
    suspend fun getFilms(filmsUrl: List<String>): List<Film>
    suspend fun getHomeLand(homeLand: String): HomeWorld
}