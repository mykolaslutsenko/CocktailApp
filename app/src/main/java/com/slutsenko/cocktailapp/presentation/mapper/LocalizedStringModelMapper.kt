package com.slutsenko.cocktailapp.presentation.mapper

import com.slutsenko.cocktailapp.data.repository.model.LocalizedStringRepoModel
import com.slutsenko.cocktailapp.presentation.mapper.base.BaseModelMapper
import com.slutsenko.cocktailapp.presentation.model.cocktail.LocalizedStringModel

/**
 * You could sometimes create object instead of class in case there is no args
 */
class LocalizedStringModelMapper : BaseModelMapper<LocalizedStringModel, LocalizedStringRepoModel>() {

    override fun mapTo(model: LocalizedStringRepoModel) = with(model) {
        LocalizedStringModel(
//            def = def,
//            defAlternate = defAlternate,
//            es = es,
//            de = de,
//            fr = fr,
//            zhHans = zhHans,
//            zhHant = zhHant
        )
    }

    override fun mapFrom(model: LocalizedStringModel) = with(model) {
        LocalizedStringRepoModel(
//            def = def,
//            defAlternate = defAlternate,
//            es = es,
//            de = de,
//            fr = fr,
//            zhHans = zhHans,
//            zhHant = zhHant
        )
    }
}
