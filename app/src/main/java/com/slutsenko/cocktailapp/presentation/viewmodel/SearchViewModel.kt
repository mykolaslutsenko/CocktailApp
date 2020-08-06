package com.slutsenko.cocktailapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel

class SearchViewModel(
        private val cocktailRepository: CocktailRepository,
        private val cocktailModelMapper: CocktailModelMapper,
        viewStateHandle: SavedStateHandle
): BaseViewModel(viewStateHandle) {

    var searchLiveData: MutableLiveData<String> = MutableLiveData()
    var answerLiveData: MutableLiveData<String> = MutableLiveData()
}