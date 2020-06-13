package com.slutsenko.cocktailapp.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.slutsenko.cocktailapp.Cocktail
import java.util.*

class CocktailList {
    @SerializedName("drinks")
    @Expose
    val cocktails = ArrayList<Cocktail>()
}