package com.example.starwarssearch.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.core.base.BaseViewHolder
import com.example.core.utils.extensions.onClick
import com.example.starwarssearch.databinding.LayoutRecentItemBinding
import com.example.starwarssearch.models.CharacterModel

class RecentAdapter(private val itemClicked: (CharacterModel) -> Unit,private val deleteItemClicked: (String) -> Unit) :
    ListAdapter<CharacterModel, RecentAdapter.RecentViewHolder>(RecentDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        val binding =
            LayoutRecentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class RecentViewHolder(private val binding: LayoutRecentItemBinding) :
        BaseViewHolder<CharacterModel>(binding) {
        override fun bindView(value: CharacterModel) {
            binding.character = value
            binding.recentCard.onClick {
                itemClicked.invoke(value)
            }

            binding.deleteBtn.onClick {
                deleteItemClicked.invoke(value.name)
            }
        }
    }

}

class RecentDiffUtil : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem == newItem
    }


}