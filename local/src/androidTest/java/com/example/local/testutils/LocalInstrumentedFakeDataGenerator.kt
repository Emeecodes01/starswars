package com.example.local.testutils


import com.example.domain.models.Character
import com.example.local.models.CharacterLocalModel
import konveyor.base.randomBuild

object LocalInstrumentedFakeDataGenerator {

    fun<T> generateListOf(count: Int, action: () -> T): List<T> {
        return mutableListOf<T>().apply {
            repeat(count){
                add(action.invoke())
            }
        }
    }

    fun getCharacter(): Character {
        return Character(
            randomBuild(), randomBuild(), randomBuild(),
            randomBuild(), generateListOf(3) { randomBuild() }, randomBuild(), randomBuild(), randomBuild(), randomBuild(),
            randomBuild(), randomBuild(), randomBuild(),
            generateListOf(2) { randomBuild() }, generateListOf(2) { randomBuild() }, randomBuild(),
            generateListOf(3) { randomBuild() }
        )
    }


    fun getCharacterLocal(name: String): CharacterLocalModel {
        return CharacterLocalModel (
            randomBuild(), randomBuild(), randomBuild(),
            randomBuild(), generateListOf(3) { randomBuild() }, randomBuild(), randomBuild(), randomBuild(), randomBuild(),
            randomBuild(), name, randomBuild(),
            generateListOf(2) { randomBuild() }, generateListOf(2) { randomBuild() }, randomBuild(),
            generateListOf(3) { randomBuild() }
        )
    }
}