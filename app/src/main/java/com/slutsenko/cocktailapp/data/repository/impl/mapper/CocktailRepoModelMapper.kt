package com.slutsenko.cocktailapp.data.repository.impl.mapper

import com.slutsenko.cocktailapp.data.db.model.CocktailDbModel
import com.slutsenko.cocktailapp.data.network.model.cocktail.CocktailNetModel
import com.slutsenko.cocktailapp.data.repository.impl.mapper.base.BaseRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.model.CocktailRepoModel

class CocktailRepoModelMapper(
        private val localizedStringRepoModelMapper: LocalizedStringRepoModelMapper
) : BaseRepoModelMapper<CocktailRepoModel, CocktailDbModel, CocktailNetModel>() {
    override fun mapDbToRepo(db: CocktailDbModel): CocktailRepoModel = with(db) {
        CocktailRepoModel(
                id = id,
                names = names.run(localizedStringRepoModelMapper::mapDbToRepo),
                isFavorite = isFavorite,
                category = category,
                alcoholType = alcoholType,
                glass = glass,
                image = image,
                instructions = instructions.run(localizedStringRepoModelMapper::mapDbToRepo),
                ingredients = ingredients,
                measures = measures,
                date = date
        )
    }

    override fun mapRepoToDb(repo: CocktailRepoModel): CocktailDbModel = with(repo) {
        CocktailDbModel(
                id = id,
                names = names.run(localizedStringRepoModelMapper::mapRepoToDb),
                isFavorite = isFavorite,
                category = category,
                alcoholType = alcoholType,
                glass = glass,
                image = image,
                instructions = instructions.run(localizedStringRepoModelMapper::mapRepoToDb),
                ingredients = ingredients,
                measures = measures,
                date = date
        )
    }

    override fun mapNetToRepo(net: CocktailNetModel): CocktailRepoModel = with(net) {
        CocktailRepoModel(
                id = id,
                names = names.run(localizedStringRepoModelMapper::mapNetToRepo),
                category = category,
                alcoholType = alcoholType,
                glass = glass,
                image = image,
                instructions = instructions.run(localizedStringRepoModelMapper::mapNetToRepo),
                ingredients = ingredients,
                measures = measures,
                date = date
        )
    }
}