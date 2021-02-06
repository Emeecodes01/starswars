package com.example.remote.impl

import com.example.core.utils.extensions.toFlow
import com.example.domain.models.Character
import com.example.domain.models.Film
import com.example.domain.models.HomeWorld
import com.example.domain.models.Species
import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.utils.LocalRepository
import com.example.domain.utils.exceptions.IllegalModuleAccessException
import com.example.remote.mappers.CharacterRemoteModelMapper
import com.example.remote.mappers.FilmRemoteModelMapper
import com.example.remote.mappers.SpeciesRemoteModelMapper
import com.example.remote.services.StarWarsService
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class StarWarsRemoteDataRepositoryImpl @Inject constructor (
    private val service: StarWarsService,
    @LocalRepository private val localRepository: StarWarsDataRepository,
    private val mapper: CharacterRemoteModelMapper,
    private val speciesRemoteModelMapper: SpeciesRemoteModelMapper,
    private val filmRemoteModelMapper: FilmRemoteModelMapper
) : StarWarsDataRepository {

    override suspend fun searchCharacter(name: String): List<Character> {
        val remoteCharacterList = service.search(name)
        localRepository.next = remoteCharacterList.next
        return remoteCharacterList.characterRemoteModels.map { mapper.mapFrom(it) }
    }

    override suspend fun saveRecent(character: Character) {
        throw IllegalModuleAccessException()
    }


    override suspend fun getSpecies(speciesUrl: List<String>): List<Species> {
        if (speciesUrl.isEmpty())
            throw Exception("There are no species available")

        return speciesUrl.toFlow()
            .map { service.fetchSpecies(it) }
            .onEach {
                val result = service.fetchHomeWord(it.homeworld.toString())
                it.homeWorld = result
            }
            .toList() // accumulate the values
            .map { speciesRemoteModelMapper.mapFrom(it) }
    }


    override suspend fun getFilms(filmsUrl: List<String>): List<Film> {
        if (filmsUrl.isEmpty())
            throw Exception("There are no films available")

        return filmsUrl.toFlow()
            .map { service.fetchFilms(it) }
            .toList()
            .map { filmRemoteModelMapper.mapFrom(it) }

    }

    override suspend fun deleteCharacter(name: String) {
        throw IllegalModuleAccessException()
    }


    override suspend fun loadMoreCharacters(): List<Character> {
        localRepository.next?.let { nextUrl ->
            val remoteCharacterList = service.loadMore(nextUrl)
            localRepository.next = remoteCharacterList.next
            return remoteCharacterList.characterRemoteModels.map { mapper.mapFrom(it) }
        } ?: run { throw  Exception("This is the end of the list")}
    }

    override fun getRecents(): Flow<List<Character>> {
        throw IllegalModuleAccessException()
    }


    override var next: String?
        get() = throw IllegalModuleAccessException()
        set(value) {throw IllegalModuleAccessException()}

}