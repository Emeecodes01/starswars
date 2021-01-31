package com.example.domain.base

import com.example.domain.thread.ExecutionThread
import kotlinx.coroutines.withContext

abstract class NoResultSuspendUseCase<in P>(
    private val executionThread: ExecutionThread
) {

    suspend operator fun invoke(params: P? = null) {
        withContext(executionThread.io()) {
            execute(params)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: P?)
}
