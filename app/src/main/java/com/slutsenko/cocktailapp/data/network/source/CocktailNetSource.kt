package com.slutsenko.cocktailapp.data.network.source

import com.slutsenko.cocktailapp.data.network.model.cocktail.CocktailNetModel

import com.slutsenko.cocktailapp.data.network.source.base.BaseNetSource

interface CocktailNetSource: BaseNetSource {
    suspend fun searchCocktail(query: String): List<CocktailNetModel>
}