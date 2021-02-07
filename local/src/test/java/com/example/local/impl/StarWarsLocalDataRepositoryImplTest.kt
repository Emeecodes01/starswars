package com.example.local.impl

import com.example.domain.models.Character
import com.example.domain.utils.exceptions.IllegalModuleAccessException
import com.example.local.database.daos.RecentCharacterDao
import com.example.local.mappers.CharacterLocalModelMapper
import com.example.local.models.CharacterLocalModel
import com.example.local.preference.IStarWarsPreferenceManager
import com.example.local.testutils.LocalFakeDataGenerator
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class StarWarsLocalDataRepositoryImplTest {

    @MockK
    lateinit var recentCharacterDao: RecentCharacterDao

    @MockK
    lateinit var preferenceManager: IStarWarsPreferenceManager

    private val mapper = CharacterLocalModelMapper()

    private lateinit var starWarsLocalDataRepositoryImpl: StarWarsLocalDataRepositoryImpl

    companion object {
        const val NAME = "default-name"
        const val SEARCH_PARAM = "search-param"
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        starWarsLocalDataRepositoryImpl = StarWarsLocalDataRepositoryImpl(recentCharacterDao, preferenceManager, mapper)
    }


    @Test
    fun `verify that saveRecentCharacter is called when saveRecent is invoke`() = runBlockingTest {
        val character = LocalFakeDataGenerator.getCharacter()
        stubRecentDaoResponse()
        starWarsLocalDataRepositoryImpl.saveRecent(character)
        val slot = slot<CharacterLocalModel>()

        coVerify (exactly = 1) { recentCharacterDao.saveRecentCharacter(capture(slot)) }

        assertThat(slot.captured.birth_year)
            .isEqualTo(character.birth_year)

        assertThat(slot.captured.eye_color)
            .isEqualTo(character.eye_color)
    }


    @Test
    fun `verify that getAllRecentCharacters when getRecents is invoked`() = runBlockingTest {
        val characters = LocalFakeDataGenerator.generateListOf(4) {LocalFakeDataGenerator.getCharacterLocal()}
        stubGetAllRecentResponse(characters)

        val result = starWarsLocalDataRepositoryImpl.getRecents().first()

        assertThat(result)
            .hasSize(characters.size)

        assertThat(result[0].birth_year)
            .isEqualTo(characters[0].birth_year)
    }


    @Test
    fun `verify that deleteCharacterWithName is called when deleteCharacter is invoked`() = runBlockingTest {
        stubDeleteRecent()
        starWarsLocalDataRepositoryImpl.deleteCharacter(NAME)
        val slot = slot<String>()

        coVerify { recentCharacterDao.deleteCharacterWithName(capture(slot)) }

        assertThat(slot.captured)
            .isEqualTo(NAME)
    }


    @Test(expected = IllegalModuleAccessException::class)
    fun `verify that when searchCharacter is called an exception is thrown`() = runBlockingTest {
        starWarsLocalDataRepositoryImpl.searchCharacter(SEARCH_PARAM)
    }


    @Test(expected = IllegalModuleAccessException::class)
    fun `verify that when getSpecies is called an exception is thrown`() = runBlockingTest {
        starWarsLocalDataRepositoryImpl.getSpecies(listOf())
    }


    @Test(expected = IllegalModuleAccessException::class)
    fun `verify that when getFilms is called an exception is thrown`() = runBlockingTest {
        starWarsLocalDataRepositoryImpl.getFilms(listOf())
    }


    @Test(expected = IllegalModuleAccessException::class)
    fun `verify that when loadMoreCharacters is called an exception is thrown`() = runBlockingTest {
        starWarsLocalDataRepositoryImpl.loadMoreCharacters()
    }


    private fun stubDeleteRecent() {
        coEvery { recentCharacterDao.deleteCharacterWithName(any()) } just runs
    }

    private fun stubGetAllRecentResponse(characters: List<CharacterLocalModel>) {
        coEvery { recentCharacterDao.getAllRecentCharacters() } returns flow { emit(characters) }
    }

    private fun stubRecentDaoResponse() {
        coEvery { recentCharacterDao.saveRecentCharacter(any()) } returns 1L
    }

}