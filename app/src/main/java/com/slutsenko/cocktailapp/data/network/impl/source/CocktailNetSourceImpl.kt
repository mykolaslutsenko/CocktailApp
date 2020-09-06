package com.slutsenko.cocktailapp.data.network.impl.source

import com.slutsenko.cocktailapp.data.network.impl.service.CocktailApiService
import com.slutsenko.cocktailapp.data.network.impl.source.base.BaseNetSourceImpl
import com.slutsenko.cocktailapp.data.network.model.cocktail.CocktailNetModel
import com.slutsenko.cocktailapp.data.network.source.CocktailNetSource


class CocktailNetSourceImpl(
        apiService: CocktailApiService
) : BaseNetSourceImpl<CocktailApiService>(apiService),
        CocktailNetSource {

    override suspend fun searchCocktail(query: String): List<CocktailNetModel> {
        return performRequest {
            searchCocktail(query).cocktails ?: emptyList()
        }
    }

    override suspend fun searchCocktailById(id: Long): CocktailNetModel {
        return performRequest {
            searchCocktailById(id).cocktails?.firstOrNull()!!
        }
    }
}
