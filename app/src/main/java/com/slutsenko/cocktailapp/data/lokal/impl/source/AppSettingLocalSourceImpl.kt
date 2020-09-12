package com.slutsenko.cocktailapp.data.lokal.impl.source

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.slutsenko.cocktailapp.data.lokal.impl.SharedPrefsHelper
import com.slutsenko.cocktailapp.data.lokal.source.AppSettingLocalSource
import com.slutsenko.common.SingletonHolder

class AppSettingLocalSourceImpl(sharedPrefsHelper: SharedPrefsHelper) : AppSettingLocalSource {

    val EXTRA_KEY_BOTTOM_TITLE = "EXTRA_KEY_BOTTOM_TITLE"
    override val showBottomTitlesLiveData: MutableLiveData<Boolean> =
            object : MutableLiveData<Boolean>(sharedPrefsHelper.getBoolean(EXTRA_KEY_BOTTOM_TITLE, true)) {
        override fun getValue(): Boolean? {
            return sharedPrefsHelper.getBoolean(EXTRA_KEY_BOTTOM_TITLE, true)
        }

        override fun setValue(value: Boolean?) {
            sharedPrefsHelper.putBoolean(EXTRA_KEY_BOTTOM_TITLE, value ?: true)
        }
    }

    companion object : SingletonHolder<AppSettingLocalSourceImpl, Context>({ context ->
        AppSettingLocalSourceImpl(SharedPrefsHelper(context.getSharedPreferences("ololo", Context.MODE_PRIVATE)))
    })


}