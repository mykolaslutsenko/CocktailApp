package com.slutsenko.cocktailapp.presentation.model.cocktail

enum class SortDrink (val key: String) {
    RECENT("recent"),
    NAME_ASCENDING("name ascending"),
    NAME_DESCENDING("name descending"),
    ALCOHOL_FIRST("alcohol first"),
    NON_ALCOHOL_FIRST("non alcohol first"),
    INGREDIENT_ASCENDING("ingredient ascending"),
    INGREDIENT_DESCENDING("ingredient descending")

}