package com.example.domain.interactors.recent

import com.example.domain.interactors.testutils.DomainFakeDataGenerator
import com.example.domain.interactors.testutils.TestExecutionThreadImpl
import com.example.domain.models.Character
import com.example.domain.repository.StarWarsDataRepository
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class SaveRecentCharacterUseCaseTest {

    private val executionThread = TestExecutionThreadImpl()

    @MockK
    lateinit var local: StarWarsDataRepository

    private lateinit var saveRecentCharacterUseCase: SaveRecentCharacterUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        saveRecentCharacterUseCase = SaveRecentCharacterUseCase(executionThread, local)
    }


    @Test
    fun `verify that when invoke is called localRepository is also called`() = runBlockingTest {
        val character = DomainFakeDataGenerator.getCharacters()
        stubResponse()
        saveRecentCharacterUseCase.invoke(character)

        coVerify(exactly = 1) { local.saveRecent(any()) }

    }


    @Test
    fun `verify that saveRecent is called with the correct params`() = runBlockingTest {
        val character = DomainFakeDataGenerator.getCharacters()
        stubResponse()
        saveRecentCharacterUseCase.invoke(character)
        val slot = CapturingSlot<Character>()

        coVerify(exactly = 1) { local.saveRecent(capture(slot)) }

        assertThat(slot.captured)
            .isEqualTo(character)
    }


    @Test(expected = IllegalStateException::class)
    fun `verify that when called with null params it throws an exception`() = runBlockingTest {
        stubResponse()
        saveRecentCharacterUseCase.invoke(null)
    }


    private fun stubResponse() {
        coEvery { local.saveRecent(any()) } just runs
    }

}