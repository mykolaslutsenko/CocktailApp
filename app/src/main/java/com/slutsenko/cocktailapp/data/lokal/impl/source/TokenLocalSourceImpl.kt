package com.slutsenko.cocktailapp.data.lokal.impl.source

import androidx.lifecycle.LiveData
import com.slutsenko.cocktailapp.data.lokal.impl.SharedPrefsHelper
import com.slutsenko.cocktailapp.data.lokal.impl.source.base.BaseLocalSourceImpl
import com.slutsenko.cocktailapp.data.lokal.source.TokenLocalSource


class TokenLocalSourceImpl(preferences: SharedPrefsHelper) :
    BaseLocalSourceImpl(preferences),
        TokenLocalSource {

    override val tokenLiveData: LiveData<String?> = sharedPrefLiveData(TOKEN, "")

    override var token: String? = sharedPrefsHelper.get(TOKEN, "")
        get() = sharedPrefsHelper.get(TOKEN, field)
        set(value) {
            sharedPrefsHelper.set(TOKEN, value)
        }

    companion object {
        const val TOKEN = "TOKEN"
    }

}