package com.slutsenko.cocktailapp.presentation.model.cocktail

interface DrinkFilter {
    val type: DrinkFilterType
    val key: String
}
