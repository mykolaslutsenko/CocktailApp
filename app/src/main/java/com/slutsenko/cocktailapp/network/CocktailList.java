package com.slutsenko.cocktailapp.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.slutsenko.cocktailapp.Cocktail;

import java.util.ArrayList;

public class CocktailList {

    @SerializedName("drinks")
    @Expose
    private ArrayList<Cocktail> drinks = new ArrayList<Cocktail>();

    public ArrayList<Cocktail> getCocktails() {
        return drinks;
    }
}
