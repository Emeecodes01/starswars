package com.example.starwarssearch.ui.viewholders

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseViewHolder
import com.example.starwarssearch.databinding.LayoutSpeciesItemBinding
import com.example.starwarssearch.databinding.LayoutSpeciesViewBinding
import com.example.starwarssearch.models.DetailAdapterItem
import com.example.starwarssearch.ui.adapters.SpeciesAdapter

class SpeciesViewHolder(val binding: LayoutSpeciesViewBinding) :
    BaseViewHolder<DetailAdapterItem.SpeciesItem>(binding) {
    private val speciesAdapter: SpeciesAdapter by lazy { SpeciesAdapter() }

    override fun bindView(value: DetailAdapterItem.SpeciesItem) {
        with(binding.speciesRv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = speciesAdapter
        }

        speciesAdapter.submitList(value.species)
    }
}