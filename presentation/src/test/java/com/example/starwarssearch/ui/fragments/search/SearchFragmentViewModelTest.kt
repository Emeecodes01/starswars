package com.example.starwarssearch.ui.fragments.search

import com.example.core.states.StarWarResource
import com.example.domain.interactors.LoadMoreCharactersUseCase
import com.example.domain.interactors.SearchStarWarsCharacterUseCase
import com.example.domain.interactors.recent.DeleteRecentCharacterUseCase
import com.example.domain.interactors.recent.GetRecentCharacterUseCase
import com.example.starwarssearch.mappers.CharacterModelMapper
import com.example.starwarssearch.testutils.MainCoroutineTestRule
import com.example.starwarssearch.testutils.PresentationFakeDataGenerator
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchFragmentViewModelTest {

    @MockK
    lateinit var searchStarWarsCharacterUseCase: SearchStarWarsCharacterUseCase

    @MockK
    lateinit var loadMoreCharactersUseCase: LoadMoreCharactersUseCase

    @MockK
    lateinit var getRecentCharacterUseCase: GetRecentCharacterUseCase

    @MockK
    lateinit var deleteRecentCharacterUseCase: DeleteRecentCharacterUseCase

    private val characterModelMapper = CharacterModelMapper()

    private lateinit var searchFragmentViewModel: SearchFragmentViewModel


    @get:Rule
    val coroutineRule = MainCoroutineTestRule()

    companion object {
        const val TEST_PARAMS = "params"
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        searchFragmentViewModel = SearchFragmentViewModel(searchStarWarsCharacterUseCase, loadMoreCharactersUseCase, getRecentCharacterUseCase,
            deleteRecentCharacterUseCase, characterModelMapper)
    }


    @Test
    fun `searchCharacter emits success when usecase invocation is successful`() {
        val data = PresentationFakeDataGenerator.generateListOf(5){ PresentationFakeDataGenerator.generateCharacter() }

        coEvery { searchStarWarsCharacterUseCase.invoke(any()) } returns data
        searchFragmentViewModel.searchCharacter(TEST_PARAMS)

        assertThat(searchFragmentViewModel.characters.value)
            .isInstanceOf(StarWarResource.Success::class.java)
    }


    @Test
    fun `searchCharacter emits error state when usecase invocation fails`() {
        coEvery { searchStarWarsCharacterUseCase.invoke(any()) } throws Exception("test error")
        searchFragmentViewModel.searchCharacter(TEST_PARAMS)

        assertThat(searchFragmentViewModel.characters.value)
            .isInstanceOf(StarWarResource.Error::class.java)
    }


    @Test
    fun `loadMore emits success when usecase invocation is successful`() {
        val data = PresentationFakeDataGenerator.generateListOf(5){ PresentationFakeDataGenerator.generateCharacter() }

        coEvery { loadMoreCharactersUseCase.invoke(any()) } returns data
        searchFragmentViewModel.loadMore()

        assertThat(searchFragmentViewModel.moreCharacters.value)
            .isInstanceOf(StarWarResource.Success::class.java)
    }


    @Test
    fun `loadMore emits error state when usecase invocation fails`() {
        coEvery { loadMoreCharactersUseCase.invoke(any()) } throws Exception("test error")
        searchFragmentViewModel.loadMore()

        assertThat(searchFragmentViewModel.moreCharacters.value)
            .isInstanceOf(StarWarResource.Error::class.java)
    }


    @Test
    fun `getCharacterLocal emits success when usecase invocation is successful`() {
        val data = PresentationFakeDataGenerator.generateListOf(5){ PresentationFakeDataGenerator.generateCharacter() }

        coEvery { getRecentCharacterUseCase.execute() } returns flow { emit(data) }
        searchFragmentViewModel.getCharacterLocal()

        assertThat(searchFragmentViewModel.recentCharacters.value)
            .isInstanceOf(StarWarResource.Success::class.java)

        assertThat(searchFragmentViewModel.recentCharacters.value.data)
            .hasSize(data.size)
    }


    @Test
    fun `getCharacterLocal emits error state when usecase invocation fails`() {
        coEvery { loadMoreCharactersUseCase.invoke(any()) } throws Exception("test error")
        searchFragmentViewModel.getCharacterLocal()

        assertThat(searchFragmentViewModel.recentCharacters.value)
            .isInstanceOf(StarWarResource.Error::class.java)
    }


    @Test
    fun `verify deleteRecentCharacterUseCase is invoked when deleteCharacterLocal is called`() {
        coEvery { deleteRecentCharacterUseCase.invoke() } just runs
        searchFragmentViewModel.deleteCharacterLocal(TEST_PARAMS)

        coVerify (exactly = 1) { deleteRecentCharacterUseCase.invoke(any()) }
    }


    @Test
    fun `verify deleteRecentCharacterUseCase is invoked with the correct params`() {
        coEvery { deleteRecentCharacterUseCase.invoke() } just runs
        searchFragmentViewModel.deleteCharacterLocal(TEST_PARAMS)

        val slot = slot<String>()
        coVerify (exactly = 1) { deleteRecentCharacterUseCase.invoke(capture(slot)) }

        assertThat(slot.captured)
            .isEqualTo(TEST_PARAMS)
    }

}