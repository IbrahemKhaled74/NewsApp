package com.example.newsapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:LoadImageFromUrl")
fun loadImage(imageView: ImageView, src: String) {
    Glide.with(imageView)
        .load(src)
        .into(imageView)
}