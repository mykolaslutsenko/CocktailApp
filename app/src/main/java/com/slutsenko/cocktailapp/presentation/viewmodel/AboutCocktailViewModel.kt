package com.slutsenko.cocktailapp.presentation.viewmodel

import android.app.Application
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.google.firebase.analytics.FirebaseAnalytics
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailModel
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel
import com.slutsenko.cocktailapp.util.FirebaseAnalyticHelper.FirebaseConstant.Companion.COCKTAIL_ID

class AboutCocktailViewModel(application: Application,
                             private val cocktailRepository: CocktailRepository,
                             private val mapper: CocktailModelMapper,
                             savedStateHandle: SavedStateHandle,
                             val firebaseAnalytics: FirebaseAnalytics

) : BaseViewModel(application) {

    var currentCocktailLiveData: MutableLiveData<CocktailModel?> = MutableLiveData()

    fun searchCocktail(id: Long):MutableLiveData<CocktailModel?> {
        val cocktailLiveData = MutableLiveData<CocktailModel?>()
        launchRequest(cocktailLiveData) {
            val cocktailDb = cocktailRepository.getCocktailById(id)
            if (cocktailDb == null) {
                val cocktailNet = cocktailRepository.searchCocktailByIdRemote(id)
                if (cocktailNet != null) {
                    cocktailRepository.addOrReplaceCocktail(cocktailNet)
                    firebaseAnalytics.logEvent(COCKTAIL_ID, bundleOf(
                            currentCocktailLiveData.value?.id.toString() to "id"))
                    mapper.mapTo(cocktailNet)
                } else {
                    null
                }
            } else {
                mapper.mapTo(cocktailDb)
            }
        }
        return cocktailLiveData
    }
}