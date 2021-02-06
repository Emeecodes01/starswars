package com.example.domain.interactors

import com.example.domain.interactors.testutils.DomainFakeDataGenerator
import com.example.domain.interactors.testutils.TestExecutionThreadImpl
import com.example.domain.models.Film
import com.example.domain.repository.StarWarsDataRepository
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import konveyor.base.randomBuild
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import java.lang.IllegalStateException

class GetFilmsUseCaseTest {

    private val executionThread = TestExecutionThreadImpl()

    @MockK
    lateinit var remote: StarWarsDataRepository

    private lateinit var getFilmsUseCase: GetFilmsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getFilmsUseCase = GetFilmsUseCase(executionThread, remote)
    }

    @Test
    fun `execute returns a list of films`() = runBlockingTest {
        val listOfFilms = DomainFakeDataGenerator.generateListOf(5){ DomainFakeDataGenerator.generateFilm() }
        stubFakeFilms(listOfFilms)
        val fakeParams = DomainFakeDataGenerator.generateListOf(2) { randomBuild(String::class.java)}
        val result = getFilmsUseCase.invoke(fakeParams)

        coVerify (exactly = 1) { remote.getFilms(any()) }

        assertThat(result[1])
            .isEqualTo(listOfFilms[1])
    }


    @Test(expected = IllegalStateException::class)
    fun `execute when called with null params throws an exception`() = runBlockingTest {
        val listOfFilms = DomainFakeDataGenerator.generateListOf(5){ DomainFakeDataGenerator.generateFilm() }
        stubFakeFilms(listOfFilms)
        getFilmsUseCase.invoke()
    }


    private fun stubFakeFilms(listOfFilms: List<Film>) {
        coEvery { remote.getFilms(any()) } returns listOfFilms
    }

}