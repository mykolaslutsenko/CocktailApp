package com.slutsenko.cocktailapp.impl

import com.slutsenko.cocktailapp.ui.fragment.FilterFragment

interface FilterResultCallback {
    val callbacks: HashSet<FilterFragment.OnFilterResultListener>
    fun addCallBack(listener: FilterFragment.OnFilterResultListener)
    fun removeCallBack(listener: FilterFragment.OnFilterResultListener)
}