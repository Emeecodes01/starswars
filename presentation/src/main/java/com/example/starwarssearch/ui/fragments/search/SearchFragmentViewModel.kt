package com.example.starwarssearch.ui.fragments.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.states.StarWarResource
import com.example.domain.interactors.LoadMoreCharactersUseCase
import com.example.domain.interactors.SearchStarWarsCharacterUseCase
import com.example.domain.interactors.recent.DeleteRecentCharacterUseCase
import com.example.domain.interactors.recent.GetRecentCharacterUseCase
import com.example.starwarssearch.mappers.CharacterModelMapper
import com.example.starwarssearch.models.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val searchStarWarsCharacterUseCase: SearchStarWarsCharacterUseCase,
    private val loadMoreCharactersUseCase: LoadMoreCharactersUseCase,
    private val getRecentCharacterUseCase: GetRecentCharacterUseCase,
    private val deleteRecentCharacterUseCase: DeleteRecentCharacterUseCase,
    private val characterModelMapper: CharacterModelMapper
) : ViewModel() {

    private val _characters: MutableStateFlow<StarWarResource<List<CharacterModel>>> =
        MutableStateFlow(StarWarResource.Empty())

    val characters: StateFlow<StarWarResource<List<CharacterModel>>> = _characters

    private val _moreCharacters: MutableStateFlow<StarWarResource<List<CharacterModel>>> =
        MutableStateFlow(StarWarResource.Empty())

    val moreCharacters: StateFlow<StarWarResource<List<CharacterModel>>> = _moreCharacters


    private val _recentCharacters: MutableStateFlow<StarWarResource<List<CharacterModel>>> =
        MutableStateFlow(StarWarResource.Empty())
    val recentCharacters: StateFlow<StarWarResource<List<CharacterModel>>> = _recentCharacters

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _characters.value = StarWarResource.Error(throwable.message)
    }

    private val _loadMoreErrorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _moreCharacters.value = StarWarResource.Error(throwable.message)
    }

    private val _recentCharactersErrorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _recentCharacters.value = StarWarResource.Error(throwable.message)
    }



    fun searchCharacter(name: String) {
        _characters.value = StarWarResource.Loading()
        viewModelScope.launch(errorHandler) {
            val chs = searchStarWarsCharacterUseCase.invoke(name)
            _characters.value = StarWarResource.Success(chs.map { characterModelMapper.mapTo(it) })
        }
    }


    fun loadMore() {
        _moreCharacters.value = StarWarResource.Loading()
        viewModelScope.launch(_loadMoreErrorHandler) {
            val moreChs = loadMoreCharactersUseCase.invoke()
            _moreCharacters.value = StarWarResource.Success(moreChs.map { characterModelMapper.mapTo(it) })
        }
    }


    fun getCharacterLocal() {
        viewModelScope.launch(_recentCharactersErrorHandler) {
            getRecentCharacterUseCase.execute().collect {
                _recentCharacters.value = StarWarResource.Success(it.map { ch -> characterModelMapper.mapTo(ch) })
            }
        }
    }


    fun deleteCharacterLocal(name: String) {
        viewModelScope.launch {
            deleteRecentCharacterUseCase.invoke(name)
        }
    }

}