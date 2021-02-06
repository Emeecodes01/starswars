package com.example.domain.interactors

import com.example.domain.interactors.testutils.DomainFakeDataGenerator
import com.example.domain.interactors.testutils.TestExecutionThreadImpl
import com.example.domain.models.Character
import com.example.domain.repository.StarWarsDataRepository
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException

class LoadMoreCharactersUseCaseTest {

    private val executionThread = TestExecutionThreadImpl()

    @MockK
    lateinit var remote: StarWarsDataRepository

    private lateinit var loadMoreCharactersUseCase: LoadMoreCharactersUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        loadMoreCharactersUseCase = LoadMoreCharactersUseCase(executionThread, remote)
    }

    @Test
    fun `returns list of character when invoke is called`() = runBlockingTest {
        val characters = DomainFakeDataGenerator.generateListOf(5) { DomainFakeDataGenerator.getCharacters() }
        stubResponse(characters)

        val result = loadMoreCharactersUseCase.invoke()

        coVerify(exactly = 1) { remote.loadMoreCharacters() }

        assertThat(result).hasSize(characters.size)
        assertThat(result[0]).isEqualTo(characters[0])
    }


    @Test
    fun `invoke runs even when called with null params`() = runBlockingTest {
        val characters = DomainFakeDataGenerator.generateListOf(5) { DomainFakeDataGenerator.getCharacters() }
        stubResponse(characters)

        loadMoreCharactersUseCase.invoke()

        coVerify(exactly = 1) { remote.loadMoreCharacters() }
    }


    private fun stubResponse(characters: List<Character>) {
        coEvery { remote.loadMoreCharacters() } returns characters
    }
}