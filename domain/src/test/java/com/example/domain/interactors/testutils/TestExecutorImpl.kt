package com.example.domain.interactors.testutils

import com.example.domain.thread.ExecutionThread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestExecutionThreadImpl : ExecutionThread {

    override fun io(): CoroutineDispatcher = TestCoroutineDispatcher()
    override fun ui(): CoroutineDispatcher = TestCoroutineDispatcher()
    override fun default(): CoroutineDispatcher = TestCoroutineDispatcher()
}