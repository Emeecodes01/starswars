package com.example.remote.mappers

import com.example.core.base.BaseMapper
import com.example.domain.models.HomeLand
import com.example.remote.models.HomeLandRemoteModel
import javax.inject.Inject

class HomeLandRemoteModelMapper @Inject constructor(): BaseMapper<HomeLandRemoteModel, HomeLand> {
    override fun mapTo(to: HomeLand): HomeLandRemoteModel {
        return HomeLandRemoteModel(
            climate = to.climate,
            created = to.created,
            diameter = to.diameter,
            edited = to.edited, films = to.films, gravity = to.gravity,
            name = to.name, orbital_period = to.orbital_period, population = to.population,
            residents = to.residents, rotation_period = to.rotation_period, surface_water = to.surface_water,
            terrain = to.terrain, url = to.url
        )
    }

    override fun mapFrom(from: HomeLandRemoteModel): HomeLand {
        return HomeLand(
            climate = from.climate,
            created = from.created,
            diameter = from.diameter,
            edited = from.edited, films = from.films, gravity = from.gravity,
            name = from.name, orbital_period = from.orbital_period, population = from.population,
            residents = from.residents, rotation_period = from.rotation_period, surface_water = from.surface_water,
            terrain = from.terrain, url = from.url
        )
    }


}