package com.example.core.utils.itemdecorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DetailsVerticalListDecorator (private val spacing: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = spacing / 2
            bottom = spacing /2
            left = spacing
            right = spacing
        }

        val position = parent.getChildAdapterPosition(view)

        if (position == 0) {
            with(outRect) {
                top = spacing
            }
        }

        val viewTypeAtPosition = parent.adapter?.getItemViewType(position)
        when(viewTypeAtPosition) {
            1 -> {
                //header view type
                with(outRect) {
                    top = spacing * 2
                    bottom = 0
                }
            }
        }

    }
}