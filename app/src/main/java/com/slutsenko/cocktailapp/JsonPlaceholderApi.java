package com.slutsenko.cocktailapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceholderApi {
    @GET("search.php")
    Call<CocktailList> getPosts(@Query("s") String s);
}
