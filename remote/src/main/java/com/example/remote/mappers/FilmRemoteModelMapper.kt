package com.example.remote.mappers

import com.example.core.base.BaseMapper
import com.example.domain.models.Film
import com.example.remote.models.FilmRemoteModel
import javax.inject.Inject

class FilmRemoteModelMapper @Inject constructor(): BaseMapper<FilmRemoteModel, Film> {

    override fun mapTo(to: Film): FilmRemoteModel {
        return FilmRemoteModel(
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

    override fun mapFrom(from: FilmRemoteModel): Film {
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