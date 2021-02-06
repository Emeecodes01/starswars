package com.example.domain.interactors.recent

import com.example.domain.base.SuspendUseCase
import com.example.domain.models.Character
import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.thread.ExecutionThread
import com.example.domain.utils.LocalRepository
import javax.inject.Inject

class SaveRecentCharacterUseCase @Inject constructor(
    executionThread: ExecutionThread,
    @LocalRepository private val localRepository: StarWarsDataRepository
): SuspendUseCase<Character, Unit>(executionThread) {

    override suspend fun execute(params: Character?) {
        checkNotNull(params)
        localRepository.saveRecent(params)
    }

}