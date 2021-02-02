package com.example.starwarssearch.mappers

import com.example.core.base.BaseMapper
import com.example.domain.models.Film
import com.example.starwarssearch.models.FilmModel
import javax.inject.Inject

class FilmModelMapper @Inject constructor(): BaseMapper<FilmModel, Film> {

    override fun mapTo(to: Film): FilmModel {
        return FilmModel(
            characters = to.characters,
            created = to.created,
            director = to.director,
            edited = to.edited,
            episode_id = to.episode_id,
            opening_crawl = to.opening_crawl,
            planets = to.planets,
            producer = to.producer,
            release_date = to.release_date,
            species = to.species,
            starships = to.starships,
            title = to.title,
            url = to.url,
            vehicles = to.vehicles
        )
    }

    override fun mapFrom(from: FilmModel): Film {
        return Film(
            characters = from.characters,
            created = from.created,
            director = from.director,
            edited = from.edited,
            episode_id = from.episode_id,
            opening_crawl = from.opening_crawl,
            planets = from.planets,
            producer = from.producer,
            release_date = from.release_date,
            species = from.species,
            starships = from.starships,
            title = from.title,
            url = from.url,
            vehicles = from.vehicles
        )
    }


}