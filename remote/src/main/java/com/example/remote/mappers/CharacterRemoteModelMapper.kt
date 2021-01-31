package com.example.remote.mappers

import com.example.core.base.BaseMapper
import com.example.domain.models.Character
import com.example.remote.models.CharacterRemoteModel
import javax.inject.Inject

class CharacterRemoteModelMapper @Inject constructor(): BaseMapper<CharacterRemoteModel, Character> {

    override fun mapTo(to: Character): CharacterRemoteModel {
        return CharacterRemoteModel(
            to.birth_year,
            to.created,
            to.edited,
            to.eye_color,
            to.films, to.gender, to.hair_color, to.height,
            to.homeworld, to.mass, to.name, to.skin_color, to.species,
            to.starships, to.url, to.vehicles
        )
    }

    override fun mapFrom(from: CharacterRemoteModel): Character {
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