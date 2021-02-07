package com.example.starwarssearch.testutils

import com.example.domain.models.Character
import com.example.domain.models.Film
import com.example.domain.models.HomeWorld
import com.example.domain.models.Species
import konveyor.base.randomBuild

object PresentationFakeDataGenerator {

    fun<T> generateListOf(count: Int, action: () -> T): List<T> {
        return mutableListOf<T>().apply {
            repeat(count){
                add(action.invoke())
            }
        }
    }


    fun generateFilm(): Film = Film(
        generateListOf(2) { randomBuild() }, randomBuild(), randomBuild(), randomBuild(),
        randomBuild(),randomBuild(),
        generateListOf(2) { randomBuild() }, randomBuild(), randomBuild(),
        generateListOf(2) { randomBuild() },
        generateListOf(2) { randomBuild() }, randomBuild(), randomBuild(),
        generateListOf(2) { randomBuild() },
    )


    fun generateSpecies(): Species = Species(
        randomBuild(), randomBuild(), randomBuild(),
        randomBuild(), randomBuild(), randomBuild(), randomBuild(),
        generateListOf(2){ randomBuild()}, randomBuild(), randomBuild(), randomBuild(),
        randomBuild(), generateListOf(2){ randomBuild()}, randomBuild(), randomBuild(),
        getHomeWorld()
    )


    private fun getHomeWorld(): HomeWorld {
        return HomeWorld (
            randomBuild(), randomBuild(), randomBuild(), randomBuild(),
            generateListOf(2){ randomBuild()},randomBuild(), randomBuild(), randomBuild(), randomBuild(),
            generateListOf(2){ randomBuild()}, randomBuild(), randomBuild(), randomBuild(), randomBuild()
        )
    }

    fun generateCharacter(): Character {
        return Character(
            randomBuild(), randomBuild(), randomBuild(),
            randomBuild(), generateListOf(3) { randomBuild() }, randomBuild(), randomBuild(), randomBuild(), randomBuild(),
            randomBuild(), randomBuild(), randomBuild(),
            generateListOf(2) { randomBuild() }, generateListOf(2) { randomBuild() }, randomBuild(),
            generateListOf(3) { randomBuild() }
        )
    }

}