package com.example.starwarssearch.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.states.StarWarResource
import com.example.domain.interactors.SearchStarWarsCharacterUseCase
import com.example.starwarssearch.mappers.CharacterModelMapper
import com.example.starwarssearch.models.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val searchStarWarsCharacterUseCase: SearchStarWarsCharacterUseCase,
    private val characterModelMapper: CharacterModelMapper
) : ViewModel() {

    private val _characters: MutableStateFlow<StarWarResource<List<CharacterModel>>> =
        MutableStateFlow(StarWarResource.Success(emptyList()))

    val characters: StateFlow<StarWarResource<List<CharacterModel>>> = _characters


    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _characters.value = StarWarResource.Error(throwable.message)
    }

    fun searchCharacter(name: String) {
        _characters.value = StarWarResource.Loading()
        viewModelScope.launch(errorHandler) {
            val chs = searchStarWarsCharacterUseCase.invoke(name)
            _characters.value = StarWarResource.Success(chs.map { characterModelMapper.mapTo(it) })
        }
    }

}