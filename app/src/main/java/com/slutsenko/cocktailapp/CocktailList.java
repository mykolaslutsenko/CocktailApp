package com.slutsenko.cocktailapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

class CocktailList {

    @SerializedName("drinks")
    @Expose
    private ArrayList<Cocktail> drinks = new ArrayList<Cocktail>();

    ArrayList<Cocktail> getCocktails() {
        return drinks;
    }
}
