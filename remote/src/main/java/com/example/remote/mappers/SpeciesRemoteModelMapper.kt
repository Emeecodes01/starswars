package com.example.remote.mappers

import com.example.core.base.BaseMapper
import com.example.domain.models.Species
import com.example.remote.models.SpeciesRemoteModel
import javax.inject.Inject

class SpeciesRemoteModelMapper @Inject constructor(
    private val homeLandRemoteModelMapper: HomeLandRemoteModelMapper
) : BaseMapper<SpeciesRemoteModel, Species> {
    override fun mapTo(to: Species): SpeciesRemoteModel {
        return SpeciesRemoteModel(
            average_height = to.average_height,
            average_lifespan = to.average_lifespan,
            classification = to.classification,
            created = to.created,
            designation = to.designation,
            edited = to.edited,
            eye_colors = to.eye_colors,
            films = to.films,
            hair_colors = to.hair_colors,
            homeworld = to.homeworld,
            language = to.language,
            name = to.name,
            people = to.people,
            skin_colors = to.skin_colors, url = to.url,
            homeWorld = homeLandRemoteModelMapper.mapTo(to.homeWorld)
        )
    }

    override fun mapFrom(from: SpeciesRemoteModel): Species {
        return Species(
            average_height = safeString(from.average_height),
            average_lifespan = safeString(from.average_lifespan),
            classification = safeString(from.classification),
            created = safeString(from.created),
            designation = safeString(from.designation),
            edited = safeString(from.edited),
            eye_colors = safeString(from.eye_colors),
            films = safeList(from.films),
            hair_colors = safeString(from.hair_colors),
            homeworld = safeString(from.homeworld),
            language = safeString(from.language),
            name = safeString(from.name),
            people = safeList(from.people),
            skin_colors = safeString(from.skin_colors), url = safeString(from.url),
            homeWorld = homeLandRemoteModelMapper.mapFrom(from.homeWorld)
        )
    }


}