package com.example.domain.interactors

import com.example.domain.base.SuspendUseCase
import com.example.domain.models.Species
import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.thread.ExecutionThread
import com.example.domain.utils.RemoteRepository
import javax.inject.Inject

class GetSpeciesUseCase @Inject constructor(
    executionThread: ExecutionThread,
    @RemoteRepository private val remote: StarWarsDataRepository
): SuspendUseCase<List<String>, List<Species>>(executionThread) {


    override suspend fun execute(params: List<String>?): List<Species> {
        checkNotNull(params)
        return remote.getSpecies(params)
    }


}