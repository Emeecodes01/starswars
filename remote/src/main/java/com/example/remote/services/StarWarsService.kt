package com.example.remote.services

import com.example.remote.models.CharacterSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsService {

    @GET("people")
    suspend fun search(@Query("search") params: String): CharacterSearchResponse

}