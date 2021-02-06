package com.example.domain.interactors.recent

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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class GetRecentCharacterUseCaseTest {

    private val executionThread = TestExecutionThreadImpl()

    @MockK
    lateinit var local: StarWarsDataRepository

    private lateinit var getRecentCharacterUseCase: GetRecentCharacterUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getRecentCharacterUseCase = GetRecentCharacterUseCase(executionThread, local)
    }

    @Test
    fun `verify that getRecents is called when execute is called`() = runBlockingTest {
        val characters = DomainFakeDataGenerator.generateListOf(5) {DomainFakeDataGenerator.getCharacters()}
        stubResponse(characters)

        getRecentCharacterUseCase.execute().first()

        coVerify(exactly = 1) { local.getRecents() }
    }


    @Test
    fun `verify that execute returns list of data`() = runBlockingTest {
        val characters = DomainFakeDataGenerator.generateListOf(5) {DomainFakeDataGenerator.getCharacters()}
        stubResponse(characters)

        val result = getRecentCharacterUseCase.execute().first()

        assertThat(result).isNotEmpty()
        assertThat(result).hasSize(characters.size)
        assertThat(result[0]).isEqualTo(characters[0])
    }

    private fun stubResponse(characters: List<Character>) {
        coEvery { local.getRecents() } returns flowOf(characters)
    }

}