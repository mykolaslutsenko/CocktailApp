package com.slutsenko.cocktailapp.data.db.impl.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.slutsenko.cocktailapp.data.db.Table
import com.slutsenko.cocktailapp.data.db.impl.dao.base.BaseDao
import com.slutsenko.cocktailapp.data.db.model.CocktailDbModel

@Dao
interface CocktailDao : BaseDao<CocktailDbModel> {

    @get:Query("SELECT * FROM ${Table.COCKTAIL}")
    val cocktailListLiveData: LiveData<List<CocktailDbModel>>

    @Query("SELECT * FROM ${Table.COCKTAIL} LIMIT 1")
    fun getFirstCocktail(): CocktailDbModel?

    @Query("SELECT * FROM ${Table.COCKTAIL} WHERE id = :id")
    fun getCocktailById(id: Long): CocktailDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addOrReplaceCocktail(cocktail: CocktailDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addOrReplaceCocktails(vararg cocktail: CocktailDbModel)

    @Transaction
    fun replaceAllCocktails(vararg cocktail: CocktailDbModel) {
        deleteAllCocktails()
        addOrReplaceCocktails(*cocktail)
    }

    @Delete
    fun deleteCocktails(vararg cocktail: CocktailDbModel)

    @Query("DELETE FROM ${Table.COCKTAIL}")
    fun deleteAllCocktails()
}