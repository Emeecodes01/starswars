package com.example.remote.impl

import com.example.core.utils.extensions.toFlow
import com.example.domain.models.Character
import com.example.domain.models.Film
import com.example.domain.models.HomeLand
import com.example.domain.models.Species
import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.utils.exceptions.IllegalModuleAccessException
import com.example.remote.mappers.CharacterRemoteModelMapper
import com.example.remote.mappers.FilmRemoteModelMapper
import com.example.remote.mappers.SpeciesRemoteModelMapper
import com.example.remote.services.StarWarsService
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class StarWarsRemoteDataRepositoryImpl @Inject constructor (
    private val service: StarWarsService,
    private val mapper: CharacterRemoteModelMapper,
    private val speciesRemoteModelMapper: SpeciesRemoteModelMapper,
    private val filmRemoteModelMapper: FilmRemoteModelMapper
) : StarWarsDataRepository {

    override suspend fun searchCharacter(name: String): List<Character> {
        val remoteCharacterList = service.search(name)
        return remoteCharacterList.characterRemoteModels.map { mapper.mapFrom(it) }
    }

    override suspend fun saveRecent(character: Character) {
        throw IllegalModuleAccessException()
    }

    override suspend fun getSpecies(speciesUrl: List<String>): List<Species> {
        return speciesUrl.toFlow()
            .map { service.fetchSpecies(it) }
            .toList() // accumulate the values
            .map { speciesRemoteModelMapper.mapFrom(it) }
    }


    override suspend fun getFilms(filmsUrl: List<String>): List<Film> {
        return filmsUrl.toFlow()
            .map { service.fetchFilms(it) }
            .toList()
            .map { filmRemoteModelMapper.mapFrom(it) }
    }

    override suspend fun getHomeLand(homeLand: String): HomeLand {
        TODO("Not yet implemented")
    }

}