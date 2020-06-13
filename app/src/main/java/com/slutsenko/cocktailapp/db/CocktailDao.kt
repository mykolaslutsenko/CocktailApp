package com.slutsenko.cocktailapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.slutsenko.cocktailapp.Cocktail

@Dao
interface CocktailDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCocktail(cocktail: Cocktail?)

    @get:Query("SELECT * FROM cocktail")
    val cocktails: List<Cocktail>
}