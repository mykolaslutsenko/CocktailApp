package com.slutsenko.cocktailapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailModel
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel

class AboutCocktailViewModel(application: Application,
                             private val cocktailRepository: CocktailRepository,
                             private val mapper: CocktailModelMapper,
                             savedStateHandle: SavedStateHandle

) : BaseViewModel(application) {

    var currentCocktailLiveData: MutableLiveData<CocktailModel>? = MutableLiveData()

    fun saveToDb(cocktail: CocktailModel) {
        launchRequest {
            cocktailRepository.addOrReplaceCocktail(mapper.mapFrom(cocktail))
        }
    }


}