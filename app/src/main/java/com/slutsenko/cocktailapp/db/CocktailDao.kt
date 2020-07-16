package com.slutsenko.cocktailapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.slutsenko.cocktailapp.entity.Cocktail

@Dao
interface CocktailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCocktail(cocktail: Cocktail?)

    @get:Query("SELECT * FROM cocktail")
    val cocktails: List<Cocktail>
}