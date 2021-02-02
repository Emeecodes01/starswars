package com.example.starwarssearch.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.core.base.BaseViewHolder
import com.example.core.utils.extensions.onClick
import com.example.starwarssearch.databinding.LayoutCharacterItemBinding
import com.example.starwarssearch.models.CharacterModel

class CharactersAdapter(private val itemClick: (CharacterModel) -> Unit) :
    ListAdapter<CharacterModel, CharactersAdapter.CharacterViewHolder>(CharacterDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            LayoutCharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }


    inner class CharacterViewHolder(private val binding: LayoutCharacterItemBinding) :
        BaseViewHolder<CharacterModel>(binding) {
        override fun bindView(value: CharacterModel) {
            binding.character = value
            binding.characterCard.onClick {
                itemClick.invoke(value)
            }
        }
    }

}