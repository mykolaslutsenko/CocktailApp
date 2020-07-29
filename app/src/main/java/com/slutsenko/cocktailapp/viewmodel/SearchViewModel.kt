package com.slutsenko.cocktailapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.slutsenko.cocktailapp.base.BaseViewModel

class SearchViewModel: BaseViewModel() {

    var searchLiveData: MutableLiveData<String> = MutableLiveData()
    var answerLiveData: MutableLiveData<String> = MutableLiveData()
}