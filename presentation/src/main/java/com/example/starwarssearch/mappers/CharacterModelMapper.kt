package com.example.starwarssearch.mappers

import com.example.core.base.BaseMapper
import com.example.domain.models.Character
import com.example.starwarssearch.models.CharacterModel
import javax.inject.Inject

class CharacterModelMapper @Inject constructor(): BaseMapper<CharacterModel, Character> {

    override fun mapTo(to: Character): CharacterModel {
        return CharacterModel(
            to.birth_year,
            to.created,
            to.edited,
            to.eye_color,
            to.films, to.gender, to.hair_color, to.height,
            to.homeworld, to.mass, to.name, to.skin_color, to.species,
            to.starships, to.url, to.vehicles
        )
    }

    override fun mapFrom(from: CharacterModel): Character {
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