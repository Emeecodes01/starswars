package com.example.core.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["cm"])
fun bindFtTextView(textView: TextView, cm: String) {
    val cmH = cm.toInt()
    val ft = cmH * 0.0328084
    textView.text = String.format("%.1f", ft) + "FT"
}