package com.slutsenko.cocktailapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.slutsenko.cocktailapp.Cocktail

@Database(entities = [Cocktail::class], version = 1, exportSchema = false)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao?
}