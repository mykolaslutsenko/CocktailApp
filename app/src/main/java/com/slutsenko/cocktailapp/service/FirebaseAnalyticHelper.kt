package com.slutsenko.cocktailapp.service

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.slutsenko.common.SingletonHolder

class FirebaseAnalyticHelper {

    companion object : SingletonHolder<FirebaseAnalytics, Context>({
        FirebaseAnalytics.getInstance(it.applicationContext)

    })

    class FirebaseConstant {
        companion object {
            const val MAIN_TAB_NAME = "MAIN_TAB_NAME"
            const val USER_AVATAR = "USER_AVATAR"
            const val USER_NAME = "USER_NAME"
            const val COCKTAIL_ID = "COCKTAIL_ID"
            const val APP_RATING = "RATING"
            const val FILTER_ALCOHOL = "FILTER_ALCOHOL"
            const val FILTER_COCKTAIL_TYPE = "FILTER_COCKTAIL_TYPE"

        }
    }


}