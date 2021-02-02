package com.example.starwarssearch.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.base.BaseViewHolder
import com.example.domain.models.Film
import com.example.starwarssearch.databinding.LayoutFilmsItemBinding
import com.example.starwarssearch.models.FilmModel

class FilmsAdapter: ListAdapter<FilmModel, FilmsAdapter.FilmsViewHolder>(FilmsDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        return FilmsViewHolder(LayoutFilmsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class FilmsViewHolder(val binding: LayoutFilmsItemBinding): BaseViewHolder<FilmModel>(binding) {
        override fun bindView(value: FilmModel) {
            binding.film = value
        }
    }

}

class FilmsDiffUtils: DiffUtil.ItemCallback<FilmModel>() {
    override fun areItemsTheSame(oldItem: FilmModel, newItem: FilmModel): Boolean {
        return newItem.opening_crawl == oldItem.opening_crawl
    }

    override fun areContentsTheSame(oldItem: FilmModel, newItem: FilmModel): Boolean {
        return oldItem == newItem
    }

}