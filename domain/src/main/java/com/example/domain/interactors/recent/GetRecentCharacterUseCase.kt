package com.example.domain.interactors.recent

import com.example.domain.base.FlowUseCase
import com.example.domain.base.SuspendUseCase
import com.example.domain.models.Character
import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.thread.ExecutionThread
import com.example.domain.utils.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentCharacterUseCase @Inject constructor(
    executionThread: ExecutionThread,
    @LocalRepository private val localRepository: StarWarsDataRepository
): FlowUseCase<Unit, List<Character>>(executionThread) {

    override fun buildFlowUseCase(params: Unit?): Flow<List<Character>> {
        return localRepository.getRecents()
    }

}