package com.slutsenko.cocktailapp.data.repository.impl.mapper

import com.slutsenko.cocktailapp.data.db.model.CocktailDbModel
import com.slutsenko.cocktailapp.data.repository.impl.mapper.base.BaseRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.model.CocktailRepoModel

class CocktailRepoModelMapper(
    private val localizedStringRepoModelMapper: LocalizedStringRepoModelMapper
): BaseRepoModelMapper<CocktailRepoModel, CocktailDbModel, Any /*CocktailNetModel*/>() {
    override fun mapDbToRepo(db: CocktailDbModel): CocktailRepoModel = with(db) {
        CocktailRepoModel(
            id = id,
            //names = names.run(localizedStringRepoModelMapper::mapDbToRepo),
            category = category,
            alcoholType = alcoholType,
            glass = glass,
            image = image,
            //instructions = instructions.run(localizedStringRepoModelMapper::mapDbToRepo),
            //ingredients = ingredients,
            //measures = measures
        )
    }

    override fun mapRepoToDb(repo: CocktailRepoModel): CocktailDbModel = with(repo) {
        CocktailDbModel(
            id = id,
            //names = names.run(localizedStringRepoModelMapper::mapRepoToDb),
            category = category,
            alcoholType = alcoholType,
            glass = glass,
            image = image,
            //instructions = instructions.run(localizedStringRepoModelMapper::mapRepoToDb),
            //ingredients = ingredients,
            //measures = measures
        )
    }
}