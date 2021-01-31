package com.example.domain.base

import com.example.domain.thread.ExecutionThread
import kotlinx.coroutines.withContext

abstract class SuspendUseCase<in PARAMS, out RESULT>(
    private val executionThread: ExecutionThread
) {

    suspend operator fun invoke(params: PARAMS? = null): RESULT {
        return withContext(executionThread.io()) {
            execute(params)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(params: PARAMS?): RESULT
}
