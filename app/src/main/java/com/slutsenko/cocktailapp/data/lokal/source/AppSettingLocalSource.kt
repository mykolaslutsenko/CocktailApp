package com.slutsenko.cocktailapp.data.lokal.source

import androidx.lifecycle.MutableLiveData

interface AppSettingLocalSource {

    val showBottomTitlesLiveData: MutableLiveData<Boolean>
}