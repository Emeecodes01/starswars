package com.example.starwarssearch.testutils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import kotlin.jvm.Throws

/**
 * Use this class whenever your viewmodel uses a coroutine
 */
@ExperimentalCoroutinesApi
class MainCoroutineTestRule: TestRule {
    private val testDispatchers = TestCoroutineDispatcher()

    override fun apply(base: Statement, description: Description): Statement {
        return TestStatement(base)
    }

    inner class TestStatement(private val base: Statement): Statement() {

        @Throws(Throwable::class)
        override fun evaluate() {

            Dispatchers.setMain(testDispatchers)
            try {
                base.evaluate()
            } finally {
                Dispatchers.resetMain()
                testDispatchers.cleanupTestCoroutines()
            }
        }
    }
}