package com.slutsenko.cocktailapp.databinding.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation


@BindingAdapter("bind:iv_loadUrl")
fun ImageView.loadUrl(url: String?) {
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("bind:regular_avatar")
fun ImageView.loadAvatar(url: String?) {
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("bind:blur_avatar")
fun ImageView.loadBlurAvatar(url: String?) {
    Glide.with(this).load(url).apply(bitmapTransform(BlurTransformation(30))).into(this)
}

