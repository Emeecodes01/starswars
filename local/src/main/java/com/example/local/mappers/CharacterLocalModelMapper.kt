package com.example.local.mappers

import com.example.core.base.BaseMapper
import com.example.domain.models.Character
import com.example.local.models.CharacterLocalModel
import javax.inject.Inject

class CharacterLocalModelMapper @Inject constructor(): BaseMapper<CharacterLocalModel, Character> {
    override fun mapTo(to: Character): CharacterLocalModel {
        return CharacterLocalModel(
            to.birth_year,
            to.created,
            to.edited,
            to.eye_color,
            to.films, to.gender, to.hair_color, to.height,
            to.homeworld, to.mass, to.name, to.skin_color, to.species,
            to.starships, to.url, to.vehicles
        )
    }

    override fun mapFrom(from: CharacterLocalModel): Character {
        return Character(
            from.birth_year,
            from.created,
            from.edited,
            from.eye_color,
            from.films, from.gender, from.hair_color, from.height,
            from.homeworld, from.mass, from.name, from.skin_color, from.species,
            from.starships, from.url, from.vehicles
        )
    }


}