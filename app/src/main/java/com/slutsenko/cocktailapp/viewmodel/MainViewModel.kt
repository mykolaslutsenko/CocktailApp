package com.slutsenko.cocktailapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.slutsenko.cocktailapp.base.BaseViewModel

class MainViewModel: BaseViewModel() {

    val showNavigationBarTitlesLiveData: MutableLiveData<Boolean> = MutableLiveData()
}