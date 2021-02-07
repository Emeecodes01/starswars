package com.example.remote.impl


import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.utils.exceptions.IllegalModuleAccessException
import com.example.remote.mappers.CharacterRemoteModelMapper
import com.example.remote.mappers.FilmRemoteModelMapper
import com.example.remote.mappers.HomeLandRemoteModelMapper
import com.example.remote.mappers.SpeciesRemoteModelMapper
import com.example.remote.models.CharacterSearchResponse
import com.example.remote.models.FilmRemoteModel
import com.example.remote.models.HomeWordRemoteModel
import com.example.remote.models.SpeciesRemoteModel
import com.example.remote.services.StarWarsService
import com.example.remote.testutils.RemoteFakeDataGenerator
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class StarWarsRemoteDataRepositoryImplTest {

    @MockK
    lateinit var service: StarWarsService

    @MockK
    lateinit var localRepository: StarWarsDataRepository

    private val characterModelMapper = CharacterRemoteModelMapper()
    private val speciesRemoteModelMapper =  SpeciesRemoteModelMapper(
        HomeLandRemoteModelMapper()
    )
    private val filmRemoteModelMapper = FilmRemoteModelMapper()

    private lateinit var starWarsRemoteDataRepositoryImpl: StarWarsRemoteDataRepositoryImpl

    companion object {
        const val SEARCH_PARAMS = "search"
        const val DEFAULT_NEXT = "default"
    }


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        starWarsRemoteDataRepositoryImpl = StarWarsRemoteDataRepositoryImpl(service, localRepository,
            characterModelMapper, speciesRemoteModelMapper, filmRemoteModelMapper)
    }


    @Test
    fun `verify searchCharacters returns a list of characters`() = runBlockingTest {
        val response = RemoteFakeDataGenerator.getCharacterResponse()
        val expected = response.characterRemoteModels
        stubResponse(response)
        val result = starWarsRemoteDataRepositoryImpl.searchCharacter(SEARCH_PARAMS)

        assertThat(result).isNotEmpty()
        assertThat(result).hasSize(expected.size)
        assertThat(result[0].birth_year).isEqualTo(expected[0].birth_year)
    }


    @Test
    fun `verify that when getSpecies is called a list of species is returned`() = runBlockingTest {
        val speciesUrl = listOf("url1", "url2")
        val species = RemoteFakeDataGenerator.generateSpecies()
        val homeWorld = RemoteFakeDataGenerator.getHomeWorld()
        stubSpeciesResponse(species)
        stubHomeWorldResponse(homeWorld)

        val result = starWarsRemoteDataRepositoryImpl.getSpecies(speciesUrl)

        assertThat(result)
            .hasSize(speciesUrl.size)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `verify that when getSpecies is called with empty url an exception is thrown`() = runBlockingTest {
        val speciesUrl: List<String> = emptyList()
        starWarsRemoteDataRepositoryImpl.getSpecies(speciesUrl)
    }


    @Test
    fun `verify that when getFilms is called a list of films is returned`() = runBlockingTest {
        val filmsUrl = listOf("url1", "url2")
        val film = RemoteFakeDataGenerator.generateFilm()
        stubFilmsResponse(film)

        val films = starWarsRemoteDataRepositoryImpl.getFilms(filmsUrl)

        assertThat(films)
            .hasSize(filmsUrl.size)
    }


    @Test(expected = IllegalArgumentException::class)
    fun `verify that when getFilms is called with empty url an exception is thrown`() = runBlockingTest {
        val filmsUrl: List<String> = emptyList()
        starWarsRemoteDataRepositoryImpl.getFilms(filmsUrl)
    }


    @Test
    fun `verify that when loadMoreCharacters is called a list of characters is returned`() = runBlockingTest {
        val characterSearchResponse = RemoteFakeDataGenerator.getCharacterResponse()
        val expected = characterSearchResponse.characterRemoteModels
        stubLoadMoreResponse(characterSearchResponse)
        coEvery { localRepository.next } returns DEFAULT_NEXT

        val characters = starWarsRemoteDataRepositoryImpl.loadMoreCharacters()
        val slot = slot<String>()

        coVerify(exactly = 1) { service.loadMore(capture(slot)) }

        assertThat(characters).hasSize(expected.size)
        assertThat(slot.captured)
            .isEqualTo(DEFAULT_NEXT)
    }


    @Test(expected = IllegalStateException::class)
    fun `verify that when loadMoreCharacters is called when next is null an exception is thrown`() = runBlockingTest {
        val characterSearchResponse = RemoteFakeDataGenerator.getCharacterResponse()
        val expected = characterSearchResponse.characterRemoteModels
        stubLoadMoreResponse(characterSearchResponse)
        coEvery { localRepository.next } returns null

        starWarsRemoteDataRepositoryImpl.loadMoreCharacters()
    }

    @Test(expected = IllegalModuleAccessException::class)
    fun `verify saveRecent throws an exception`() = runBlockingTest {
        val character = RemoteFakeDataGenerator.getCharactersDomain()
        starWarsRemoteDataRepositoryImpl.saveRecent(character)
    }

    @Test(expected = IllegalModuleAccessException::class)
    fun `verify that when getRecent is called an exception is called`() = runBlockingTest {
        starWarsRemoteDataRepositoryImpl.getRecents()
    }

    @Test(expected = IllegalModuleAccessException::class)
    fun `verify that setting next throws an exception`() = runBlockingTest {
        starWarsRemoteDataRepositoryImpl.next = "dsf"
    }

    @Test(expected = IllegalModuleAccessException::class)
    fun `verify that getting next throws an exception`() = runBlockingTest {
        val next = starWarsRemoteDataRepositoryImpl.next
    }

    @Test(expected = IllegalModuleAccessException::class)
    fun `verify that deleteCharacter throws an exception`() = runBlockingTest {
        starWarsRemoteDataRepositoryImpl.deleteCharacter("name")
    }


    private fun stubResponse(response: CharacterSearchResponse) {
        coEvery { service.search(any()) } returns response
    }

    private fun stubHomeWorldResponse(homeWorld: HomeWordRemoteModel) {
        coEvery { service.fetchHomeWord(any()) } returns homeWorld
    }

    private fun stubSpeciesResponse(species: SpeciesRemoteModel) {
        coEvery { service.fetchSpecies(any()) } returns species
    }

    private fun stubFilmsResponse(film: FilmRemoteModel) {
        coEvery { service.fetchFilms(any()) } returns film
    }

    private fun stubLoadMoreResponse(moreSearch: CharacterSearchResponse) {
        coEvery { service.loadMore(any()) } returns moreSearch
    }

}