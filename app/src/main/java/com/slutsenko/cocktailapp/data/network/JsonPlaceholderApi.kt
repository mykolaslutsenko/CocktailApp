package com.slutsenko.cocktailapp.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonPlaceholderApi {
    @GET("search.php")
    fun getPosts(@Query("s") s: String?): Call<CocktailList?>?
}