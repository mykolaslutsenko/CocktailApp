package com.slutsenko.cocktailapp.data.repository.source

import androidx.lifecycle.MutableLiveData
import com.slutsenko.cocktailapp.data.repository.source.base.BaseRepository

interface AppSettingRepository :BaseRepository{
    val mutableLiveData: MutableLiveData<Boolean>
}