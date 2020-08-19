package com.slutsenko.cocktailapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel

class MainActivityViewModel (application: Application,
                             private val cocktailRepository: CocktailRepository,
                             private val mapper: CocktailModelMapper,
                             savedStateHandle: SavedStateHandle
): BaseViewModel(application) {

}