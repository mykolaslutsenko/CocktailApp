package com.slutsenko.cocktailapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.google.firebase.analytics.FirebaseAnalytics
import com.slutsenko.cocktailapp.data.repository.source.AppSettingRepository
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel

class MainActivityViewModel(application: Application,
                            private val cocktailRepository: CocktailRepository,
                            appSettingRepository: AppSettingRepository,
                            private val mapper: CocktailModelMapper,
                            savedStateHandle: SavedStateHandle,
                            val firebaseAnalytics: FirebaseAnalytics

) : BaseViewModel(application) {

    var showNavigationBarTitlesLiveData: MutableLiveData<Boolean> =
            MutableLiveData(appSettingRepository.mutableLiveData.value ?: false)

    //appSettingRepository.mutableLiveData


}