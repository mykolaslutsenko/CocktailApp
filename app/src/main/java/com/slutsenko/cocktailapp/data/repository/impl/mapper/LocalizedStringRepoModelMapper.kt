package com.slutsenko.cocktailapp.data.repository.impl.mapper

import com.slutsenko.cocktailapp.data.db.model.LocalizedStringDbModel
import com.slutsenko.cocktailapp.data.repository.impl.mapper.base.BaseRepoModelMapper
import com.slutsenko.cocktailapp.data.repository.model.LocalizedStringRepoModel

class LocalizedStringRepoModelMapper :
    BaseRepoModelMapper<LocalizedStringRepoModel, LocalizedStringDbModel, Any /*CocktailNetModel*/>() {
    override fun mapDbToRepo(db: LocalizedStringDbModel) = with(db) {
        LocalizedStringRepoModel(
            def = def,
            defAlternate = defAlternate,
            es = es,
            de = de,
            fr = fr,
            zhHans = zhHans,
            zhHant = zhHant
        )
    }

    override fun mapRepoToDb(repo: LocalizedStringRepoModel) = with(repo) {
        LocalizedStringDbModel(
            def = def,
            defAlternate = defAlternate,
            es = es,
            de = de,
            fr = fr,
            zhHans = zhHans,
            zhHant = zhHant
        )
    }
}