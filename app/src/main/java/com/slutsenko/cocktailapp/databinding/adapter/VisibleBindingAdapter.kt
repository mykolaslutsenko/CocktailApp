package com.slutsenko.cocktailapp.databinding.adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("bind:isVisible")
fun View.isViewVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}