package com.example.domain.thread

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ExecutionThreadImpl @Inject constructor() : ExecutionThread {
    override fun io(): CoroutineDispatcher = Dispatchers.IO
    override fun ui(): CoroutineDispatcher = Dispatchers.Main
    override fun default(): CoroutineDispatcher = Dispatchers.Default
}