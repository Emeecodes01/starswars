package com.example.starwarssearch.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarssearch.databinding.LayoutCharacterDetailBinding
import com.example.starwarssearch.databinding.LayoutFilmsViewBinding
import com.example.starwarssearch.databinding.LayoutHeaderBinding
import com.example.starwarssearch.databinding.LayoutSpeciesViewBinding
import com.example.starwarssearch.models.DetailAdapterItem
import com.example.starwarssearch.ui.viewholders.CharacterDetailsViewHolder
import com.example.starwarssearch.ui.viewholders.FilmViewHolder
import com.example.starwarssearch.ui.viewholders.HeaderViewHolder
import com.example.starwarssearch.ui.viewholders.SpeciesViewHolder

class DetailFragmentAdapter: ListAdapter<DetailAdapterItem, RecyclerView.ViewHolder>(DetailAdapterDiffUtil()){

    private val detailList: MutableList<DetailAdapterItem> = mutableListOf()

    fun addItem(item: DetailAdapterItem) {
        detailList.add(item)
        submitList(detailList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            HEADER_VIEW_TYPE -> HeaderViewHolder(LayoutHeaderBinding.inflate(inflater, parent ,false))
            CHARACTER_VIEW_TYPE -> CharacterDetailsViewHolder(LayoutCharacterDetailBinding.inflate(inflater, parent, false))
            SPECIES_VIEW_TYPE -> SpeciesViewHolder(LayoutSpeciesViewBinding.inflate(inflater, parent, false))
            FILMS_VIEW_TYPE -> FilmViewHolder(LayoutFilmsViewBinding.inflate(inflater, parent, false))
            else -> throw Exception("Unknown view type $viewType")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is HeaderViewHolder -> holder.bindView(getItem(position) as DetailAdapterItem.HeaderItem)
            is CharacterDetailsViewHolder -> holder.bindView(getItem(position) as DetailAdapterItem.CharacterItem)
            is SpeciesViewHolder -> holder.bindView(getItem(position) as DetailAdapterItem.SpeciesItem)
            is FilmViewHolder -> holder.bindView(getItem(position) as DetailAdapterItem.FilmsItem)
        }
    }



    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is DetailAdapterItem.HeaderItem -> HEADER_VIEW_TYPE
            is DetailAdapterItem.CharacterItem -> CHARACTER_VIEW_TYPE
            is DetailAdapterItem.SpeciesItem -> SPECIES_VIEW_TYPE
            is DetailAdapterItem.FilmsItem -> FILMS_VIEW_TYPE
            else -> throw Exception("Item type not known")
        }
    }


    companion object {
        const val HEADER_VIEW_TYPE = 1
        const val CHARACTER_VIEW_TYPE = 2
        const val SPECIES_VIEW_TYPE = 3
        const val FILMS_VIEW_TYPE = 4
    }

}