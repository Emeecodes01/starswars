package com.example.starwarssearch.ui.viewholders

import com.example.core.base.BaseViewHolder
import com.example.starwarssearch.databinding.LayoutHeaderBinding
import com.example.starwarssearch.models.DetailAdapterItem

class HeaderViewHolder(private val binding: LayoutHeaderBinding): BaseViewHolder<DetailAdapterItem.HeaderItem>(binding) {
    override fun bindView(value: DetailAdapterItem.HeaderItem) {
        binding.textView7.text = value.title
    }
}