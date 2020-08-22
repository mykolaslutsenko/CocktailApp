package com.slutsenko.cocktailapp.presentation.mapper

import com.slutsenko.cocktailapp.data.repository.model.CocktailRepoModel
import com.slutsenko.cocktailapp.presentation.mapper.base.BaseModelMapper
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailAlcoholType
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailCategory
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailGlass
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailModel

class CocktailModelMapper(
        private val localizedStringModelMapper: LocalizedStringModelMapper
) : BaseModelMapper<CocktailModel, CocktailRepoModel>() {

    override fun mapFrom(model: CocktailModel) = with(model) {
        CocktailRepoModel(
                id = id,
                isFavorite = isFavorite,
                names = names.run(localizedStringModelMapper::mapFrom),
                category = category.key,
                alcoholType = alcoholType.key,
                glass = glass.key,
                image = image,
                instructions = instructions.run(localizedStringModelMapper::mapFrom),
                ingredients = ingredients,
                measures = measures,
                date = date
        )
    }

    override fun mapTo(model: CocktailRepoModel) = with(model) {
        CocktailModel(
                id = id,
                isFavorite = isFavorite,
                names = names.run(localizedStringModelMapper::mapTo),
                category = CocktailCategory.values().firstOrNull { it.key == category }
                        ?: CocktailCategory.UNDEFINED,
                alcoholType = CocktailAlcoholType.values().firstOrNull { it.key == alcoholType }
                        ?: CocktailAlcoholType.UNDEFINED,
                glass = CocktailGlass.values().firstOrNull { it.key == glass }
                        ?: CocktailGlass.UNDEFINED,
                image = image,
                instructions = instructions.run(localizedStringModelMapper::mapTo),
                ingredients = ingredients,
                measures = measures,
                date = date
        )
    }
}
