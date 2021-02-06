package com.example.starwarssearch.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.base.BaseViewHolder
import com.example.core.utils.extensions.onClick
import com.example.core.utils.itemdecorators.FilmsHorizontalDecoration
import com.example.starwarssearch.R
import com.example.starwarssearch.databinding.LayoutCharacterItemBinding
import com.example.starwarssearch.databinding.LayoutLoadMoreBinding
import com.example.starwarssearch.databinding.LayoutRecentBinding
import com.example.starwarssearch.databinding.LayoutRecentItemBinding
import com.example.starwarssearch.models.CharacterModel
import com.example.starwarssearch.models.SearchAdapterItem
import java.lang.IllegalStateException

class CharactersAdapter(private val itemClick: (CharacterModel) -> Unit) :
    ListAdapter<SearchAdapterItem, RecyclerView.ViewHolder>(CharacterDiffUtilCallback()) {


    private val loadingItem = SearchAdapterItem.LoadingItem()
    var charactersAdapterListener: CharactersAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            LOADING_ITEM_TYPE -> {
                LoadMoreViewHolder(LayoutLoadMoreBinding.inflate(inflater, parent, false))
            }
            RECENT_ITEM_TYPE -> {
                RecentViewHolder(LayoutRecentBinding.inflate(inflater, parent, false))
            }
            SEARCH_ITEM_TYPE -> {
                CharacterViewHolder(LayoutCharacterItemBinding.inflate(inflater, parent, false))
            }
            else -> throw Exception("Unsupported view type...")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> {
                holder.bindView(getItem(position) as SearchAdapterItem.SearchItem)
            }

            is LoadMoreViewHolder -> {
                holder.bindView(getItem(position) as SearchAdapterItem.LoadingItem)
            }

            is RecentViewHolder -> {
                holder.bindView(getItem(position) as SearchAdapterItem.RecentItem)
            }
            else -> throw IllegalStateException("Unknown View Holder...")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SearchAdapterItem.LoadingItem -> LOADING_ITEM_TYPE
            is SearchAdapterItem.RecentItem -> RECENT_ITEM_TYPE
            is SearchAdapterItem.SearchItem -> SEARCH_ITEM_TYPE
            else -> throw IllegalStateException("Unknown view type")
        }
    }

    companion object {
        const val LOADING_ITEM_TYPE = 1
        const val RECENT_ITEM_TYPE = 2
        const val SEARCH_ITEM_TYPE = 3
    }


    inner class CharacterViewHolder(private val binding: LayoutCharacterItemBinding) :
        BaseViewHolder<SearchAdapterItem.SearchItem>(binding) {
        override fun bindView(value: SearchAdapterItem.SearchItem) {
            binding.character = value.character
            binding.characterCard.onClick {
                itemClick.invoke(value.character)
            }
        }
    }


    inner class LoadMoreViewHolder(private val binding: LayoutLoadMoreBinding) :
        BaseViewHolder<SearchAdapterItem.LoadingItem>(binding)

    inner class RecentViewHolder(private val binding: LayoutRecentBinding) :
        BaseViewHolder<SearchAdapterItem.RecentItem>(binding) {
        private val recentAdapter: RecentAdapter by lazy {
            RecentAdapter({ characterModel: CharacterModel ->
                charactersAdapterListener?.onRecentItemClicked(characterModel)
            }, { characterName: String ->
                charactersAdapterListener?.onDeleteRecentItemClicked(characterName)
            })
        }

        override fun bindView(value: SearchAdapterItem.RecentItem) {
            val recents = value.recents
            with(binding.recentRv) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(
                    FilmsHorizontalDecoration(
                        context.resources.getDimension(R.dimen.compact_padding).toInt()
                    )
                )
                adapter = recentAdapter
            }
            recentAdapter.submitList(recents)
        }

    }

    interface CharactersAdapterListener {
        fun onRecentItemClicked(characterModel: CharacterModel)
        fun onDeleteRecentItemClicked(name: String)
    }
}