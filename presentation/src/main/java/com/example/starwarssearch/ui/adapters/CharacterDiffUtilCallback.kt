package com.example.starwarssearch.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.starwarssearch.models.CharacterModel
import com.example.starwarssearch.models.SearchAdapterItem

class CharacterDiffUtilCallback: DiffUtil.ItemCallback<SearchAdapterItem>() {
    override fun areItemsTheSame(oldItem: SearchAdapterItem, newItem: SearchAdapterItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchAdapterItem, newItem: SearchAdapterItem): Boolean {
        return oldItem == newItem
    }
}