package com.example.remote.testutils

import com.example.domain.models.Character
import com.example.remote.models.*
import konveyor.base.randomBuild

object RemoteFakeDataGenerator {

    fun<T> generateListOf(count: Int, action: () -> T): List<T> {
        return mutableListOf<T>().apply {
            repeat(count){
                add(action.invoke())
            }
        }
    }


    fun getCharacters(): CharacterRemoteModel {
        return CharacterRemoteModel(
            randomBuild(), randomBuild(), randomBuild(),
            randomBuild(), generateListOf(3) { randomBuild() }, randomBuild(), randomBuild(), randomBuild(), randomBuild(),
            randomBuild(), randomBuild(), randomBuild(),
            generateListOf(2) { randomBuild() }, generateListOf(2) { randomBuild() }, randomBuild(),
            generateListOf(3) { randomBuild() }
        )
    }


    fun generateFilm(): FilmRemoteModel = FilmRemoteModel(
        generateListOf(2){randomBuild()}, randomBuild(), randomBuild(), randomBuild(),
        randomBuild(),randomBuild(), generateListOf(2){randomBuild()}, randomBuild(), randomBuild(),
        generateListOf(2){randomBuild()},
        generateListOf(2){randomBuild()}, randomBuild(), randomBuild(), generateListOf(2){randomBuild()},
    )


    fun generateSpecies(): SpeciesRemoteModel = SpeciesRemoteModel(
        randomBuild(), randomBuild(), randomBuild(),
        randomBuild(), randomBuild(), randomBuild(), randomBuild(),
        generateListOf(2){ randomBuild()}, randomBuild(), randomBuild(), randomBuild(),
        randomBuild(), generateListOf(2){ randomBuild()}, randomBuild(), randomBuild(),
        getHomeWorld()
    )


    fun getHomeWorld(): HomeWordRemoteModel {
        return HomeWordRemoteModel (
            randomBuild(), randomBuild(), randomBuild(), randomBuild(),
            generateListOf(2){ randomBuild()},randomBuild(), randomBuild(), randomBuild(), randomBuild(),
            generateListOf(2){ randomBuild()}, randomBuild(), randomBuild(), randomBuild(), randomBuild()
        )
    }


    fun getCharacterResponse(): CharacterSearchResponse =
        CharacterSearchResponse(randomBuild(), randomBuild(), randomBuild(),
        generateListOf(3){ getCharacters()})


    fun getCharactersDomain(): Character = Character(
        randomBuild(), randomBuild(), randomBuild(),
        randomBuild(), generateListOf(3) { randomBuild() }, randomBuild(), randomBuild(), randomBuild(), randomBuild(),
        randomBuild(), randomBuild(), randomBuild(),
        generateListOf(2) { randomBuild() }, generateListOf(2) { randomBuild() }, randomBuild(),
        generateListOf(3) { randomBuild() }
    )
}