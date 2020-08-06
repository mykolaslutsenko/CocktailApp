package com.slutsenko.cocktailapp.data.db.model

import androidx.room.ColumnInfo

data class LocalizedStringDbModel(
    @ColumnInfo(name = "def")
    var def: String? = null,

    @ColumnInfo(name = "defAlternate")
    var defAlternate: String? = null,

    @ColumnInfo(name = "es")
    var es: String? = null,

    @ColumnInfo(name = "de")
    var de: String? = null,

    @ColumnInfo(name = "fr")
    var fr: String? = null,

    @ColumnInfo(name = "zn_hans")
    var zhHans: String? = null,

    @ColumnInfo(name = "zn_hant")
    var zhHant: String? = null
)