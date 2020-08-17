package com.slutsenko.cocktailapp.data.repository.source

import androidx.lifecycle.LiveData
import com.slutsenko.cocktailapp.data.repository.model.CocktailRepoModel
import com.slutsenko.cocktailapp.data.repository.source.base.BaseRepository

interface CocktailRepository : BaseRepository {

    val cocktailListLiveData: LiveData<List<CocktailRepoModel>>
//    suspend fun hasCocktails(): Boolean
    fun getCocktailLiveData() : List<CocktailRepoModel>
    suspend fun getFirstCocktail(): CocktailRepoModel?
    suspend fun getCocktailById(id: Long): CocktailRepoModel?
    suspend fun addOrReplaceCocktail(cocktail: CocktailRepoModel)
    suspend fun addOrReplaceCocktails(vararg cocktail: CocktailRepoModel)
    suspend fun replaceAllCocktails(vararg cocktail: CocktailRepoModel)
    suspend fun deleteCocktails(vararg cocktail: CocktailRepoModel)
    suspend fun deleteAllCocktails()
}