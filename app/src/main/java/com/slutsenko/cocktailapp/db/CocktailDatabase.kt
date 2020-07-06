package com.slutsenko.cocktailapp.db

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.slutsenko.cocktailapp.entity.Cocktail

@Database(entities = [Cocktail::class], version = 1, exportSchema = true)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao?

    companion object {
        private var instance: CocktailDatabase? = null


        @Synchronized
        fun getInstance(context: Context): CocktailDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context,
                        CocktailDatabase::class.java, "cocktail11").allowMainThreadQueries().build()
            }
            return instance
        }
    }
}