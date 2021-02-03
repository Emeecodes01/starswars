package com.example.starwarssearch.models

import com.example.core.states.ItemState

sealed class DetailAdapterItem {
    abstract val id: String
    abstract val state: ItemState
    abstract val errorMessage: String?

    data class CharacterItem(
        val characterModel: CharacterModel,
        override val state: ItemState = ItemState.IDLE, override val errorMessage: String? = null
    ) : DetailAdapterItem() {
        override val id: String
            get() = characterModel.name
    }


    data class HeaderItem(
        val title: String,
        override val state: ItemState = ItemState.IDLE,
        override val errorMessage: String? = null
    ) :
        DetailAdapterItem() {
        override val id: String
            get() = title
    }


    data class SpeciesItem(
        val species: List<SpeciesModel>,
        override val state: ItemState = ItemState.IDLE, override val errorMessage: String? = null
    ) : DetailAdapterItem() {
        override val id: String
            get() = "species_item"
    }


    data class FilmsItem(
        val films: List<FilmModel>,
        override val state: ItemState = ItemState.IDLE, override val errorMessage: String? = null
    ) : DetailAdapterItem() {
        override val id: String
            get() = "films_item"
    }
}