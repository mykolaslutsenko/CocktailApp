package com.slutsenko.cocktailapp.data.network.model.responce

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.slutsenko.cocktailapp.data.network.model.cocktail.CocktailNetModel

data class CocktailListResponseNetModel(
        @SerializedName("drinks")
        @Expose
        val cocktails: List<CocktailNetModel>?
)
