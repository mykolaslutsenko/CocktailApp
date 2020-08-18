package com.slutsenko.cocktailapp.data.repository.source

import androidx.lifecycle.MutableLiveData

interface AppSettingRepository {
    val mutableLiveData: MutableLiveData<Boolean>
}