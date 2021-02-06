package com.example.domain.interactors.recent

import com.example.domain.base.SuspendUseCase
import com.example.domain.models.Character
import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.thread.ExecutionThread
import com.example.domain.utils.LocalRepository
import javax.inject.Inject

class DeleteRecentCharacterUseCase @Inject constructor (
    executionThread: ExecutionThread,
    @LocalRepository private val localRepository: StarWarsDataRepository
): SuspendUseCase<String, Unit>(executionThread) {

    override suspend fun execute(params: String?) {
        checkNotNull(params)
        localRepository.deleteCharacter(params)
    }

}