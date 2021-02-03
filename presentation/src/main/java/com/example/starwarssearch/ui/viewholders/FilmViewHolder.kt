package com.example.starwarssearch.ui.viewholders

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseStatefulViewHolder
import com.example.core.base.BaseViewHolder
import com.example.core.utils.extensions.gone
import com.example.core.utils.extensions.invisible
import com.example.core.utils.extensions.visible
import com.example.core.utils.itemdecorators.FilmsHorizontalDecoration
import com.example.starwarssearch.R
import com.example.starwarssearch.databinding.LayoutFilmsViewBinding
import com.example.starwarssearch.models.DetailAdapterItem
import com.example.starwarssearch.models.FilmModel
import com.example.starwarssearch.ui.adapters.FilmsAdapter

class FilmViewHolder(private val binding: LayoutFilmsViewBinding) :
    BaseStatefulViewHolder<DetailAdapterItem.FilmsItem>(binding) {
    private val filmAdapter: FilmsAdapter by lazy { FilmsAdapter() }

    override fun errorState(message: String?) {
        with(binding) {
            errorTv.text = message
            progress.gone()
            errorTv.visible()
            filmsRv.invisible()
        }
    }

    override fun loadingState() {
        with(binding) {
            progress.visible()
            errorTv.gone()
            filmsRv.invisible()
        }
    }

    override fun successState(value: DetailAdapterItem.FilmsItem) {
        with(binding) {
            progress.gone()
            errorTv.gone()
            filmsRv.visible()
        }

        with(binding.filmsRv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(FilmsHorizontalDecoration(context.resources.getDimension(R.dimen.compact_padding).toInt()))
            adapter = filmAdapter
        }
        filmAdapter.submitList(value.films)
    }

}