package com.slutsenko.cocktailapp.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class CocktailList {
    @SerializedName("drinks")
    @Expose
    val cocktails = ArrayList<CocktailNetModel>()
}