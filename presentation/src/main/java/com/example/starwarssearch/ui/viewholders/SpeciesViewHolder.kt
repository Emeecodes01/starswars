package com.example.starwarssearch.ui.viewholders

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseStatefulViewHolder
import com.example.core.base.BaseViewHolder
import com.example.core.states.ItemState
import com.example.core.utils.extensions.gone
import com.example.core.utils.extensions.invisible
import com.example.core.utils.extensions.visible
import com.example.starwarssearch.databinding.LayoutSpeciesItemBinding
import com.example.starwarssearch.databinding.LayoutSpeciesViewBinding
import com.example.starwarssearch.models.DetailAdapterItem
import com.example.starwarssearch.ui.adapters.SpeciesAdapter

class SpeciesViewHolder(val binding: LayoutSpeciesViewBinding) :
    BaseStatefulViewHolder<DetailAdapterItem.SpeciesItem>(binding) {

    private val speciesAdapter: SpeciesAdapter by lazy { SpeciesAdapter() }

    override fun errorState(message: String?) {
        with(binding) {
            errorTv.text = message
            progress.gone()
            errorTv.visible()
            speciesRv.invisible()
        }
    }


    override fun loadingState() {
        with(binding) {
            progress.visible()
            errorTv.gone()
            speciesRv.invisible()
        }
    }


    override fun successState(value: DetailAdapterItem.SpeciesItem) {
        with(binding) {
            progress.gone()
            errorTv.gone()
            speciesRv.visible()
        }

        with(binding.speciesRv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = speciesAdapter
        }
        speciesAdapter.submitList(value.species)
    }
}