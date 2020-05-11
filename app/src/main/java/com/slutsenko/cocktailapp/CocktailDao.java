package com.slutsenko.cocktailapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CocktailDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void addCocktail(Cocktail cocktail);

    @Query("SELECT * FROM cocktail")
    List<Cocktail> getCocktails();


}
