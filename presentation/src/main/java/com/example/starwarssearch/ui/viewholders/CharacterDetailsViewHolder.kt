package com.example.starwarssearch.ui.viewholders

import com.example.core.base.BaseViewHolder
import com.example.starwarssearch.databinding.LayoutCharacterDetailBinding
import com.example.starwarssearch.models.CharacterModel
import com.example.starwarssearch.models.DetailAdapterItem


class CharacterDetailsViewHolder(private val binding: LayoutCharacterDetailBinding) :
    BaseViewHolder<DetailAdapterItem.CharacterItem>(binding) {

    override fun bindView(value: DetailAdapterItem.CharacterItem) {
        binding.character = value.characterModel
    }

}