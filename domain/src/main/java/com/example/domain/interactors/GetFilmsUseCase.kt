package com.example.domain.interactors

import com.example.domain.base.SuspendUseCase
import com.example.domain.models.Film
import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.thread.ExecutionThread
import com.example.domain.utils.RemoteRepository
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(
    executionThread: ExecutionThread,
    @RemoteRepository private val remote: StarWarsDataRepository
): SuspendUseCase<List<String>, List<Film>>(executionThread) {

    override suspend fun execute(params: List<String>?): List<Film> {
        checkNotNull(params)
        return remote.getFilms(params)
    }

}