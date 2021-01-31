package com.example.remote.models

import com.google.gson.annotations.SerializedName

data class CharacterSearchResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    @SerializedName("results")
    val characterRemoteModels: List<CharacterRemoteModel>
)