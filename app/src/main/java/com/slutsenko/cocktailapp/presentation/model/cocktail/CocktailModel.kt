package com.slutsenko.cocktailapp.presentation.model.cocktail

data class CocktailModel(
        val id: Long = -1L,
        var isFavorite:Boolean = false,
        val names: LocalizedStringModel = LocalizedStringModel(),
        val category: CocktailCategory = CocktailCategory.UNDEFINED,
        val alcoholType: CocktailAlcoholType = CocktailAlcoholType.UNDEFINED,
        val glass: CocktailGlass = CocktailGlass.UNDEFINED,
        val image: String = "",
        val instructions: LocalizedStringModel = LocalizedStringModel(),
        val ingredients: List<CocktailIngredient> = emptyList(),
        val measures: List<String> = emptyList()/*,
    val date: Date = Date()*/
)