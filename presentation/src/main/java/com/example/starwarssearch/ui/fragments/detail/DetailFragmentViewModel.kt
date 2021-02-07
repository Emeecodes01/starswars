package com.example.starwarssearch.ui.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.states.StarWarResource
import com.example.domain.interactors.GetFilmsUseCase
import com.example.domain.interactors.GetSpeciesUseCase
import com.example.domain.interactors.recent.SaveRecentCharacterUseCase
import com.example.starwarssearch.mappers.CharacterModelMapper
import com.example.starwarssearch.mappers.FilmModelMapper
import com.example.starwarssearch.mappers.SpeciesModelMapper
import com.example.starwarssearch.models.CharacterModel
import com.example.starwarssearch.models.FilmModel
import com.example.starwarssearch.models.SpeciesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor (
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val getFilmsUseCase: GetFilmsUseCase,
    private val saveRecentCharacterUseCase: SaveRecentCharacterUseCase,
    private val speciesModelMapper: SpeciesModelMapper,
    private val filmsModelMapper: FilmModelMapper,
    private val characterModelMapper: CharacterModelMapper
) : ViewModel() {

    private val _species: MutableStateFlow<StarWarResource<List<SpeciesModel>>> =
        MutableStateFlow(StarWarResource.Success(emptyList()))
    val species: StateFlow<StarWarResource<List<SpeciesModel>>> = _species

    private val _films: MutableStateFlow<StarWarResource<List<FilmModel>>> =
        MutableStateFlow(StarWarResource.Success(emptyList()))
    val films: StateFlow<StarWarResource<List<FilmModel>>> = _films

    private val detailsErrorHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }



    private suspend fun getSpecies(speciesUrl: List<String>): List<SpeciesModel> {
        _species.value = StarWarResource.Loading()
        val speciesResult = getSpeciesUseCase.invoke(speciesUrl)
        return speciesResult.map { speciesModelMapper.mapTo(it) }
    }


    private suspend fun getFilms(films: List<String>): List<FilmModel> {
        _films.value = StarWarResource.Loading()
        val filmsResult = getFilmsUseCase.invoke(films)
        return filmsResult.map { filmsModelMapper.mapTo(it) }
    }

    fun getAllCharacterDetails(character: CharacterModel) {
        _species.value = StarWarResource.Loading()
        viewModelScope.launch(detailsErrorHandler) {

            supervisorScope {
                val speciesDeferred = async { getSpecies(character.species) }
                val filmsDeferred = async { getFilms(character.films) }

                runCatching { speciesDeferred.await() }
                    .onSuccess {
                        _species.value = StarWarResource.Success(it)
                    }
                    .onFailure {
                        _species.value = StarWarResource.Error(it.message)
                    }


                runCatching { filmsDeferred.await() }
                    .onSuccess {
                        _films.value = StarWarResource.Success(it)
                    }
                    .onFailure {
                        _films.value = StarWarResource.Error(it.message)
                    }
            }

        }
    }

    fun saveRecentMovie(character: CharacterModel) {
        viewModelScope.launch {
            saveRecentCharacterUseCase.invoke(characterModelMapper.mapFrom(character))
        }
    }

}