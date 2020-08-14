package com.slutsenko.cocktailapp.presentation.model.cocktail

import java.io.Serializable

class LocalizedStringModel(
    val def: String? = null,
    val defAlternate: String? = null,
    val es: String? = null,
    val de: String? = null,
    val fr: String? = null,
    val zhHans: String? = null,
    val zhHant: String? = null
) : Serializable