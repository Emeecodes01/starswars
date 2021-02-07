package com.example.starwarssearch.ui.fragments.detail

import com.example.core.states.StarWarResource
import com.example.domain.interactors.GetFilmsUseCase
import com.example.domain.interactors.GetSpeciesUseCase
import com.example.domain.interactors.recent.SaveRecentCharacterUseCase
import com.example.domain.models.Character
import com.example.domain.models.Film
import com.example.domain.models.Species
import com.example.starwarssearch.mappers.CharacterModelMapper
import com.example.starwarssearch.mappers.FilmModelMapper
import com.example.starwarssearch.mappers.HomeWorldModelMapper
import com.example.starwarssearch.mappers.SpeciesModelMapper
import com.example.starwarssearch.models.CharacterModel
import com.example.starwarssearch.testutils.MainCoroutineTestRule
import com.example.starwarssearch.testutils.PresentationFakeDataGenerator
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailFragmentViewModelTest {

    @get:Rule
    val testRule = MainCoroutineTestRule()

    @MockK
    lateinit var getSpeciesUseCase: GetSpeciesUseCase

    @MockK
    lateinit var getFilmsUseCase: GetFilmsUseCase

    @MockK
    lateinit var saveRecentCharacterUseCase: SaveRecentCharacterUseCase

    private val speciesModelMapper = SpeciesModelMapper(HomeWorldModelMapper())

    private val filmModelMapper = FilmModelMapper()

    private val characterModelMapper = CharacterModelMapper()

    private lateinit var detailFragmentViewModel: DetailFragmentViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        detailFragmentViewModel = DetailFragmentViewModel(getSpeciesUseCase, getFilmsUseCase, saveRecentCharacterUseCase,
        speciesModelMapper, filmModelMapper, characterModelMapper)
    }


    @Test
    fun `getAllCharacterDetails emits species and films when their usecase invocation is successful`() {
        val films = PresentationFakeDataGenerator.generateListOf(5) { PresentationFakeDataGenerator.generateFilm() }
        val species = PresentationFakeDataGenerator.generateListOf(4) {PresentationFakeDataGenerator.generateSpecies() }
        val character = characterModelMapper.mapTo(PresentationFakeDataGenerator.generateCharacter())

        stubFilms(films)
        stubSpecies(species)

        detailFragmentViewModel.getAllCharacterDetails(character)

        assertThat(detailFragmentViewModel.films.value)
            .isInstanceOf(StarWarResource.Success::class.java)

        assertThat(detailFragmentViewModel.films.value.data)
            .hasSize(films.size)

        assertThat(detailFragmentViewModel.species.value)
            .isInstanceOf(StarWarResource.Success::class.java)

        assertThat(detailFragmentViewModel.species.value.data)
            .hasSize(species.size)
    }


    @Test
    fun `getAllCharacterDetails emits species even when films usecase invocation fails`() {
        val films = PresentationFakeDataGenerator.generateListOf(5) { PresentationFakeDataGenerator.generateFilm() }
        val species = PresentationFakeDataGenerator.generateListOf(4) {PresentationFakeDataGenerator.generateSpecies() }
        val character = characterModelMapper.mapTo(PresentationFakeDataGenerator.generateCharacter())

        stubFilmsError(films)
        stubSpecies(species)

        detailFragmentViewModel.getAllCharacterDetails(character)

        assertThat(detailFragmentViewModel.films.value)
            .isInstanceOf(StarWarResource.Error::class.java)


        assertThat(detailFragmentViewModel.species.value)
            .isInstanceOf(StarWarResource.Success::class.java)

        assertThat(detailFragmentViewModel.species.value.data)
            .hasSize(species.size)
    }


    @Test
    fun `getAllCharacterDetails emits films when species  usecase invocation fails`() {
        val films = PresentationFakeDataGenerator.generateListOf(5) { PresentationFakeDataGenerator.generateFilm() }
        val species = PresentationFakeDataGenerator.generateListOf(4) {PresentationFakeDataGenerator.generateSpecies() }
        val character = characterModelMapper.mapTo(PresentationFakeDataGenerator.generateCharacter())

        stubFilms(films)
        stubSpeciesError(species)

        detailFragmentViewModel.getAllCharacterDetails(character)

        assertThat(detailFragmentViewModel.films.value)
            .isInstanceOf(StarWarResource.Success::class.java)

        assertThat(detailFragmentViewModel.films.value.data)
            .hasSize(films.size)

        assertThat(detailFragmentViewModel.species.value)
            .isInstanceOf(StarWarResource.Error::class.java)
    }


    @Test
    fun `verify that when saveRecentMovie is called saveRecentCharacterUseCase is invoked`() {
        val character = PresentationFakeDataGenerator.generateCharacter()
        val characterModel = characterModelMapper.mapTo(character)

        coEvery { saveRecentCharacterUseCase.invoke(any()) } just runs

        detailFragmentViewModel.saveRecentMovie(characterModel)

        coVerify (exactly = 1){ saveRecentCharacterUseCase.invoke(any()) }
    }


    @Test
    fun `verify that when saveRecentMovie is called saveRecentCharacterUseCase is invoked with the correct params`() {
        val character = PresentationFakeDataGenerator.generateCharacter()
        val characterModel = characterModelMapper.mapTo(character)

        coEvery { saveRecentCharacterUseCase.invoke(any()) } just runs

        detailFragmentViewModel.saveRecentMovie(characterModel)

        val slot = slot<Character>()
        coVerify (exactly = 1){ saveRecentCharacterUseCase.invoke(capture(slot)) }

        assertThat(slot.captured)
            .isEqualTo(character)
    }


    private fun stubSpecies(species: List<Species>) {
        coEvery { getSpeciesUseCase.invoke(any()) } returns species
    }

    private fun stubSpeciesError(species: List<Species>) {
        coEvery { getSpeciesUseCase.invoke(any()) } throws Exception("Test exception...")
    }

    private fun stubFilms(films: List<Film>) {
        coEvery { getFilmsUseCase.invoke(any()) } returns films
    }

    private fun stubFilmsError(films: List<Film>) {
        coEvery { getFilmsUseCase.invoke(any()) } throws Exception("Test exception...")
    }

}