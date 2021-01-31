package com.example.remote.impl

import com.example.domain.models.Character
import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.utils.exceptions.IllegalModuleAccessException
import com.example.remote.mappers.CharacterRemoteModelMapper
import com.example.remote.services.StarWarsService
import javax.inject.Inject

class StarWarsRemoteDataRepositoryImpl @Inject constructor (
    private val service: StarWarsService,
    private val mapper: CharacterRemoteModelMapper
) : StarWarsDataRepository {

    override suspend fun searchCharacter(name: String): List<Character> {
        val remoteCharacterList = service.search(name)
        return remoteCharacterList.characterRemoteModels.map { mapper.mapFrom(it) }
    }

    override suspend fun saveRecent(character: Character) {
        throw IllegalModuleAccessException()
    }

}