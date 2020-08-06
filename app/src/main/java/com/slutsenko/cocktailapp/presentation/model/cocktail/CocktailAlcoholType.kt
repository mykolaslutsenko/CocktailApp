package com.slutsenko.cocktailapp.presentation.model.cocktail

enum class CocktailAlcoholType(override val type: DrinkFilterType, override val key: String): DrinkFilter {
    ALCOHOLIC(DrinkFilterType.ALCOHOL, "Alcoholic"),
    NON_ALCOHOLIC(DrinkFilterType.ALCOHOL, "Non alcoholic"),
    OPTIONAL_ALCOHOL(DrinkFilterType.ALCOHOL, "Optional alcohol"),
    UNDEFINED (DrinkFilterType.ALCOHOL, "")
}
