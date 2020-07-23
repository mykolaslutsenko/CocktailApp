package com.slutsenko.cocktailapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.slutsenko.cocktailapp.base.BaseViewModel
import com.slutsenko.cocktailapp.entity.Cocktail

class AboutCocktailViewModel: BaseViewModel() {

    var cocktailLiveData: MutableLiveData<Cocktail> = MutableLiveData()
}