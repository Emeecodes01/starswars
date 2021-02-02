package com.example.starwarssearch.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.starwarssearch.models.DetailAdapterItem

class DetailAdapterDiffUtil : DiffUtil.ItemCallback<DetailAdapterItem>() {
    override fun areItemsTheSame(oldItem: DetailAdapterItem, newItem: DetailAdapterItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DetailAdapterItem,
        newItem: DetailAdapterItem
    ): Boolean {
        return oldItem == newItem
    }


}