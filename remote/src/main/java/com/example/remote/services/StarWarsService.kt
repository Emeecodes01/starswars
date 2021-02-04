package com.example.remote.services

import com.example.remote.models.CharacterSearchResponse
import com.example.remote.models.FilmRemoteModel
import com.example.remote.models.HomeWordRemoteModel
import com.example.remote.models.SpeciesRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsService {

    @GET("people")
    suspend fun search(@Query("search") params: String): CharacterSearchResponse

    @GET
    suspend fun fetchSpecies(@Url speciesUrl: String): SpeciesRemoteModel

    @GET
    suspend fun fetchFilms(@Url filmUrl: String): FilmRemoteModel

    @GET
    suspend fun fetchHomeWord(@Url homeWorld: String): HomeWordRemoteModel

}