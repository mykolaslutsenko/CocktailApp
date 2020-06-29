package com.slutsenko.cocktailapp.filter

import com.slutsenko.cocktailapp.impl.DrinkFilter

enum class AlcoholDrinkFilter(override val type: DrinkFilterType, override val key: String): DrinkFilter {
    ALCOHOLIC(DrinkFilterType.ALCOHOL, "Alcoholic"),
    NON_ALCOHOLIC(DrinkFilterType.ALCOHOL, "Non alcoholic"),
    OPTIONAL_ALCOHOL(DrinkFilterType.ALCOHOL, "Optional alcohol"),
    NON (DrinkFilterType.ALCOHOL, "Non")
}
