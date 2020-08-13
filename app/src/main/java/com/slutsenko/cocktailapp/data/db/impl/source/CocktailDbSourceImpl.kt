package com.slutsenko.cocktailapp.data.db.impl.source

import androidx.lifecycle.LiveData
import com.slutsenko.cocktailapp.data.db.impl.dao.CocktailDao
import com.slutsenko.cocktailapp.data.db.model.CocktailDbModel
import com.slutsenko.cocktailapp.data.db.source.CocktailDbSource

class CocktailDbSourceImpl(
        private val cocktailDao: CocktailDao
) : CocktailDbSource {

    override val cocktailListLiveData: LiveData<List<CocktailDbModel>> =
            cocktailDao.cocktailListLiveData

    override  fun getCocktailLiveData(): List<CocktailDbModel> = cocktailDao.getCocktailLiveData()


    //
//    override suspend fun hasCocktails() = cocktailDao.getFirstCocktail() != null
//
    override suspend fun getFirstCocktail() = cocktailDao.getFirstCocktail()

    override suspend fun getCocktailById(id: Long) = cocktailDao.getCocktailById(id)

    //
    override suspend fun addOrReplaceCocktail(cocktail: CocktailDbModel) {
        cocktailDao.addOrReplaceCocktail(cocktail)
    }

    override suspend fun addOrReplaceCocktails(vararg cocktail: CocktailDbModel) {
        cocktailDao.addOrReplaceCocktails(*cocktail)
    }
//
//    override suspend fun replaceAllCocktails(vararg cocktail: CocktailDbModel) {
//        cocktailDao.replaceAllCocktails(*cocktail)
//    }
//
//    override suspend fun deleteCocktails(vararg cocktail: CocktailDbModel) {
//        cocktailDao.deleteCocktails(*cocktail)
//    }
//
//    override suspend fun deleteAllCocktails() {
//        cocktailDao.deleteAllCocktails()
//    }
}