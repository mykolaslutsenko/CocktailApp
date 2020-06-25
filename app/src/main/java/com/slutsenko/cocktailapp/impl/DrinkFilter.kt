package com.slutsenko.cocktailapp.impl

import com.slutsenko.cocktailapp.filter.DrinkFilterType

interface DrinkFilter {
    val type: DrinkFilterType
    val key: String
}
