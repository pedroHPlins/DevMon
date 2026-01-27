package com.example.devmon.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")

    fun loadImage(view: ImageView, url: String) {
        if (url.isNotEmpty()) {
            Glide.with(view.context).load(url)
        }
    }
}