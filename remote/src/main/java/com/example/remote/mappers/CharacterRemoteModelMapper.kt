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
            safeString(from.birth_year),
            safeString(from.created),
            safeString(from.edited),
            safeString(from.eye_color),
            safeList(from.films), safeString(from.gender), safeString(from.hair_color), safeString(from.height),
            safeString(from.homeworld), safeString(from.mass), safeString(from.name), safeString(from.skin_color), safeList(from.species),
            safeList(from.starships), safeString(from.url), safeList(from.vehicles)
        )
    }
}