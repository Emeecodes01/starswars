package com.example.core.utils.itemdecorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FilmsHorizontalDecoration(private val spacing: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            left = spacing / 2
            right = spacing / 2
        }
    }
}