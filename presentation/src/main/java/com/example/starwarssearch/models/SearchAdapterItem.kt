package com.example.starwarssearch.models

sealed class SearchAdapterItem {
    abstract val id: String

    data class LoadingItem(private val title: String = "load"): SearchAdapterItem() {
        override val id: String
            get() = title
    }

    data class RecentItem(val recents: List<CharacterModel>): SearchAdapterItem(){
        override val id: String
            get() = "_recent_list"
    }

    data class SearchItem(val character: CharacterModel): SearchAdapterItem() {
        override val id: String
            get() = "characters"
    }

}