package com.example.starwarssearch.mappers

import com.example.core.base.BaseMapper
import com.example.domain.models.HomeWorld
import com.example.starwarssearch.models.HomeWorldModel
import javax.inject.Inject

class HomeWorldModelMapper @Inject constructor(): BaseMapper<HomeWorldModel?, HomeWorld?>{

    override fun mapTo(to: HomeWorld?): HomeWorldModel? {
        return HomeWorldModel(
            climate = safeString(to?.climate),
            created = safeString(to?.created),
            diameter = safeString(to?.diameter),
            edited = safeString(to?.edited),
            films = safeList(to?.films),
            gravity = safeString(to?.gravity),
            name = safeString(to?.name),
            orbital_period = safeString(to?.orbital_period),
            population = safeString(to?.population),
            residents = safeList(to?.residents),
            rotation_period = safeString(to?.rotation_period),
            surface_water = safeString(to?.surface_water),
            terrain = safeString(to?.terrain),
            url = safeString(to?.url)
        )
    }

    override fun mapFrom(from: HomeWorldModel?): HomeWorld? {
        return HomeWorld(
            climate = safeString(from?.climate),
            created = safeString(from?.created),
            diameter = safeString(from?.diameter),
            edited = safeString(from?.edited),
            films = safeList(from?.films),
            gravity = safeString(from?.gravity),
            name = safeString(from?.name),
            orbital_period = safeString(from?.orbital_period),
            population = safeString(from?.population),
            residents = safeList(from?.residents),
            rotation_period = safeString(from?.rotation_period),
            surface_water = safeString(from?.surface_water),
            terrain = safeString(from?.terrain),
            url = safeString(from?.url)
        )
    }
}