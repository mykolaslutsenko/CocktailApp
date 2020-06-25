package com.slutsenko.cocktailapp.filter

import com.slutsenko.cocktailapp.impl.DrinkFilter

enum class AlcoholDrinkFilter(override val type: DrinkFilterType, override val key: String): DrinkFilter {
    ALCOHOLIC(DrinkFilterType.ALCOHOL, "Alcoholic"),
    NON_ALCOHOLIC(DrinkFilterType.ALCOHOL, "Non_alcoholic"),
    OPTIONAL_ALCOHOL(DrinkFilterType.ALCOHOL, "Optional_alcohol"),
    NON (DrinkFilterType.ALCOHOL, "Non")
}
