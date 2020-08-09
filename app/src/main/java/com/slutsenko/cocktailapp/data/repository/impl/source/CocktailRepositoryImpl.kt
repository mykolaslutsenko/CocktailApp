package com.slutsenko.cocktailapp.data.repository.impl.source

import com.slutsenko.cocktailapp.data.db.source.CocktailDbSource
import com.slutsenko.cocktailapp.data.repository.impl.mapper.CocktailRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.impl.source.base.BaseRepositoryImpl
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository

class CocktailRepositoryImpl(
        private val dbSource: CocktailDbSource,
        private val mapper: CocktailRepoModelMapper
) : BaseRepositoryImpl(), CocktailRepository {

//    override val cocktailListLiveData: LiveData<List<CocktailRepoModel>> =
//        dbSource.cocktailListLiveData.map(mapper::mapDbToRepo)
//
//    override suspend fun hasCocktails() = dbSource.hasCocktails()
//
//    override suspend fun getFirstCocktail(): CocktailRepoModel? {
//        return dbSource.getFirstCocktail()?.run(mapper::mapDbToRepo)
//    }
//
//    override suspend fun getCocktailById(id: Long): CocktailRepoModel? {
//        return dbSource.getCocktailById(id)?.run(mapper::mapDbToRepo)
//    }
//
//    override suspend fun addOrReplaceCocktail(cocktail: CocktailRepoModel) {
//        dbSource.addOrReplaceCocktail(cocktail.run(mapper::mapRepoToDb))
//    }
//
//    override suspend fun addOrReplaceCocktails(vararg cocktail: CocktailRepoModel) {
//        dbSource.addOrReplaceCocktails(
//            *cocktail.map(mapper::mapRepoToDb).toTypedArray()
//        )
//    }
//
//    override suspend fun replaceAllCocktails(vararg cocktail: CocktailRepoModel) {
//        dbSource.replaceAllCocktails(
//            *cocktail.map(mapper::mapRepoToDb).toTypedArray()
//        )
//    }
//
//    override suspend fun deleteCocktails(vararg cocktail: CocktailRepoModel) {
//        dbSource.deleteCocktails(
//            *cocktail.map(mapper::mapRepoToDb).toTypedArray()
//        )
//    }
//
//    override suspend fun deleteAllCocktails() {
//        dbSource.deleteAllCocktails()
//    }
}
