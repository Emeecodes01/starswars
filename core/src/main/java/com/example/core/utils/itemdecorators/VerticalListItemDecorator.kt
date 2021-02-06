package com.example.core.utils.itemdecorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalListItemDecorator(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = spacing / 2
            left = spacing
            right = spacing
            bottom = spacing / 2
        }

        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            with(outRect) {
                top = spacing
            }
        }

        if (position > -1) {
            val viewType = parent.adapter?.getItemViewType(position)
            viewType?.let {
                if (it == 2) {
                    with(outRect) {
                        left = 0
                        right = 0
                    }
                }
            }
        }

    }
}