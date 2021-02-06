package com.example.local.impl

import com.example.domain.models.Character
import com.example.domain.models.Film
import com.example.domain.models.HomeWorld
import com.example.domain.models.Species
import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.utils.exceptions.IllegalModuleAccessException
import com.example.local.database.daos.RecentCharacterDao
import com.example.local.mappers.CharacterLocalModelMapper
import com.example.local.preference.IStarWarsPreferenceManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StarWarsLocalDataRepositoryImpl @Inject constructor (
    private val recentCharacterDao: RecentCharacterDao,
    private val preferenceManager: IStarWarsPreferenceManager,
    private val characterLocalModelMapper: CharacterLocalModelMapper
): StarWarsDataRepository {
    override suspend fun searchCharacter(name: String): List<Character> {
        throw IllegalModuleAccessException()
    }

    override suspend fun saveRecent(character: Character) {
        val characterModel = characterLocalModelMapper.mapTo(character)
        characterModel.timeFetched = System.currentTimeMillis()
        recentCharacterDao.saveRecentCharacter(characterModel)
    }

    override suspend fun getSpecies(speciesUrl: List<String>): List<Species> {
        throw IllegalModuleAccessException()
    }

    override suspend fun getFilms(filmsUrl: List<String>): List<Film> {
        throw IllegalModuleAccessException()
    }

    override suspend fun deleteCharacter(name: String) {
        recentCharacterDao.deleteCharacterWithName(name)
    }

    override suspend fun loadMoreCharacters(): List<Character> {
        throw IllegalModuleAccessException()
    }

    override fun getRecents(): Flow<List<Character>> {
        return  recentCharacterDao.getAllRecentCharacters().flatMapConcat { charactersLocal ->
            flowOf(charactersLocal.map { characterLocalModelMapper.mapFrom(it) })
        }
    }


    override var next: String?
        get() = preferenceManager.next
        set(value) {preferenceManager.next = value}

}