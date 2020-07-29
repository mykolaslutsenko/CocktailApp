package com.slutsenko.cocktailapp.databinding.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("bind:iv_loadUrl")
fun ImageView.loadUrl(url: String?) {
    Glide.with(this).load(url).into(this)
}

