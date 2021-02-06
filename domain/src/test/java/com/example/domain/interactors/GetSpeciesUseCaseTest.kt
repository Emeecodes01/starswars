package com.example.domain.interactors

import com.example.domain.interactors.testutils.DomainFakeDataGenerator
import com.example.domain.interactors.testutils.TestExecutionThreadImpl
import com.example.domain.models.Species
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
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class GetSpeciesUseCaseTest {

    private val executionThread = TestExecutionThreadImpl()

    @MockK
    lateinit var remote: StarWarsDataRepository

    private lateinit var getSpeciesUseCase: GetSpeciesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getSpeciesUseCase = GetSpeciesUseCase(executionThread, remote)
    }



    @Test
    fun `invoke returns a list of species`() = runBlockingTest {
        val species = DomainFakeDataGenerator.generateListOf(5){DomainFakeDataGenerator.generateSpecies()}
        stubResponse(species)

        val result = getSpeciesUseCase.invoke(listOf("string1", "string2"))

        coVerify(exactly = 1 ) { remote.getSpecies(any()) }

        assertThat(result[0]).isEqualTo(species[0])
        assertThat(result).hasSize(species.size)
    }



    @Test(expected = IllegalStateException::class)
    fun `invoke fails when called with null params`() = runBlockingTest {

        val species = DomainFakeDataGenerator.generateListOf(5){DomainFakeDataGenerator.generateSpecies()}
        stubResponse(species)
        getSpeciesUseCase.invoke()
    }



    private fun stubResponse(species: List<Species>) {
        coEvery { remote.getSpecies(any()) } returns species
    }
}