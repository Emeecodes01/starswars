package com.example.starwarssearch.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.base.BaseViewHolder
import com.example.starwarssearch.databinding.LayoutSpeciesItemBinding
import com.example.starwarssearch.models.SpeciesModel

class SpeciesAdapter :
    ListAdapter<SpeciesModel, SpeciesAdapter.SpeciesViewHolder>(SpeciesDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        return SpeciesViewHolder(
            LayoutSpeciesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class SpeciesViewHolder(val binding: LayoutSpeciesItemBinding) :
        BaseViewHolder<SpeciesModel>(binding) {
        override fun bindView(value: SpeciesModel) {
            binding.species = value
        }
    }

}

class SpeciesDiffUtils : DiffUtil.ItemCallback<SpeciesModel>() {
    override fun areItemsTheSame(oldItem: SpeciesModel, newItem: SpeciesModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: SpeciesModel, newItem: SpeciesModel): Boolean {
        return oldItem == newItem
    }

}