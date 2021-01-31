package com.example.core.utils.extensions

import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}


fun View.onClick(action: () -> Unit) {
    setOnClickListener {
        action.invoke()
    }
}