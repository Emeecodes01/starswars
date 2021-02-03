package com.example.core.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.core.states.ItemState


/**
 * Just a View Holder that tracks state, i can make T to be a sub-type of DetailItem but i want to make this
 * class as loosely coupled as possible
 */
abstract class BaseStatefulViewHolder<T>(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
    fun bindView(value: T, state: ItemState, errMessage: String? = null) {
        when(state) {
            ItemState.IDLE -> { }
            ItemState.LOADING -> loadingState()
            ItemState.SUCCESS -> successState(value)
            ItemState.ERROR -> {errorState(errMessage)}
        }
    }

    abstract fun errorState(message: String?)
    abstract fun loadingState()
    abstract fun successState(value: T)
}