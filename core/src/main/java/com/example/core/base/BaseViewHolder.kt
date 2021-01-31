package com.example.core.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
    open fun bindView(value: T){}
}