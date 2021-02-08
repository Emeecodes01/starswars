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
            characters = safeList(from.characters),
            created = safeString(from.created),
            director = safeString(from.director),
            edited = safeString(from.edited),
            episode_id = safeInt(from.episode_id),
            opening_crawl = safeString(from.opening_crawl),
            planets = safeList(from.planets),
            producer = safeString(from.producer),
            release_date = safeString(from.release_date),
            species = safeList(from.species),
            starships = safeList(from.starships),
            title = safeString(from.title),
            url = safeString(from.url),
            vehicles = safeList(from.vehicles)
        )
    }


}