package com.example.starwarssearch.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.starwarssearch.models.CharacterModel

class CharacterDiffUtilCallback: DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem == newItem
    }
}