package com.example.domain.interactors

import com.example.domain.base.SuspendUseCase
import com.example.domain.models.Character
import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.thread.ExecutionThread
import com.example.domain.utils.RemoteRepository
import javax.inject.Inject

class LoadMoreCharactersUseCase @Inject constructor(
    executionThread: ExecutionThread,
    @RemoteRepository private val remoteRepository: StarWarsDataRepository
): SuspendUseCase<Unit, List<Character>>(executionThread) {

    override suspend fun execute(params: Unit?): List<Character> {
        return remoteRepository.loadMoreCharacters()
    }


}