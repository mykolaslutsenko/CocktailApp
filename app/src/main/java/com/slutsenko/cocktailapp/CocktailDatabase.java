package com.slutsenko.cocktailapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Cocktail.class, version = 1, exportSchema = false)
public abstract class CocktailDatabase extends RoomDatabase {

    public abstract CocktailDao cocktailDao();
}
