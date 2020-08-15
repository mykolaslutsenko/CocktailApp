package com.slutsenko.cocktailapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailModel
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel

class AboutCocktailViewModel(private val cocktailRepository: CocktailRepository,
                             private val mapper: CocktailModelMapper,
                             savedStateHandle: SavedStateHandle

) : BaseViewModel() {

    var currentCocktailLiveData: MutableLiveData<CocktailModel>? = MutableLiveData()

    fun saveToDb(cocktail:CocktailModel) {
        launchRequest {
            cocktailRepository.addOrReplaceCocktail(mapper.mapFrom(cocktail))
        }
    }


}