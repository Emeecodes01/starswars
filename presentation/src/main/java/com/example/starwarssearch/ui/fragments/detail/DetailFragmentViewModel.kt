package com.example.starwarssearch.ui.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.states.StarWarResource
import com.example.domain.interactors.GetFilmsUseCase
import com.example.domain.interactors.GetSpeciesUseCase
import com.example.starwarssearch.mappers.FilmModelMapper
import com.example.starwarssearch.mappers.SpeciesModelMapper
import com.example.starwarssearch.models.CharacterModel
import com.example.starwarssearch.models.FilmModel
import com.example.starwarssearch.models.SpeciesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val getSpeciesUseCase: GetSpeciesUseCase,
    private val getFilmsUseCase: GetFilmsUseCase,
    private val speciesModelMapper: SpeciesModelMapper,
    private val filmsModelMapper: FilmModelMapper
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

    private suspend fun getSpecies(speciesUrl: List<String>): List<SpeciesModel>{
        _species.value = StarWarResource.Loading()
        val speciesResult = getSpeciesUseCase.invoke(speciesUrl)
        return speciesResult.map { speciesModelMapper.mapTo(it) }
    }


    private suspend fun getFilms(films: List<String>): List<FilmModel> {
        _films.value = StarWarResource.Loading()
        val filmsResult = getFilmsUseCase.invoke(films)
        return filmsResult.map { filmsModelMapper.mapTo(it) }
    }

    private suspend fun getHomeLand(url: String) {

    }


    fun getAllCharacterDetails(character: CharacterModel) {
        viewModelScope.launch(detailsErrorHandler) {
            val speciesDeffered = async { getSpecies(character.species) }
            val filmsDeffered = async { getFilms(character.films) }

            val speciesResult = speciesDeffered.await()
            _species.value = StarWarResource.Success(speciesResult)

            val filmsResult = filmsDeffered.await()
            _films.value = StarWarResource.Success(filmsResult)

        }
    }


}