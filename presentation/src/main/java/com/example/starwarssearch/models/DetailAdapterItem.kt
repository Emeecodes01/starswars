package com.example.starwarssearch.models

sealed class DetailAdapterItem {
    abstract val id: String
    data class CharacterItem(val characterModel: CharacterModel): DetailAdapterItem() {
        override val id: String
            get() = characterModel.name
    }


    data class HeaderItem(val title: String): DetailAdapterItem() {
        override val id: String
            get() = title
    }


    data class SpeciesItem(val species: List<SpeciesModel>): DetailAdapterItem() {
        override val id: String
            get() = "species_item"
    }


    data class FilmsItem(val films: List<FilmModel>): DetailAdapterItem() {
        override val id: String
            get() = "films_item"
    }

}