package com.example.domain.interactors

import com.example.domain.interactors.testutils.DomainFakeDataGenerator
import com.example.domain.interactors.testutils.TestExecutionThreadImpl
import com.example.domain.models.Character
import com.example.domain.repository.StarWarsDataRepository
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class SearchStarWarsCharacterUseCaseTest {

    private val executionThread = TestExecutionThreadImpl()

    @MockK
    lateinit var remote: StarWarsDataRepository

    private lateinit var searchStarWarsCharacterUseCase: SearchStarWarsCharacterUseCase

    companion object {
        const val CHARACTER_PARAMS = "r2"
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        searchStarWarsCharacterUseCase = SearchStarWarsCharacterUseCase(executionThread, remote)
    }

    @Test
    fun `invoke returns characters when called with a search params`() = runBlockingTest {
        val characters = DomainFakeDataGenerator.generateListOf(5) { DomainFakeDataGenerator.getCharacters() }
        stubResponse(characters)

        val result = searchStarWarsCharacterUseCase.invoke("r2")

        assertThat(result).isNotEmpty()
        assertThat(result).hasSize(characters.size)
    }

    @Test(expected = IllegalStateException::class)
    fun `invoke throws an error when called with null params`() = runBlockingTest {
        val characters = DomainFakeDataGenerator.generateListOf(5) { DomainFakeDataGenerator.getCharacters() }
        stubResponse(characters)

        searchStarWarsCharacterUseCase.invoke(null)
    }

    private fun stubResponse(characters: List<Character>) {
        coEvery { remote.searchCharacter(any()) } returns characters
    }
}