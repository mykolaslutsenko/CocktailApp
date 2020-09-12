package com.slutsenko.cocktailapp.data.repository.impl.source

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.slutsenko.cocktailapp.data.lokal.impl.source.AppSettingLocalSourceImpl
import com.slutsenko.cocktailapp.data.lokal.source.AppSettingLocalSource
import com.slutsenko.cocktailapp.data.repository.source.AppSettingRepository
import com.slutsenko.common.SingletonHolder

class AppSettingRepositoryImpl private constructor(private val localSource: AppSettingLocalSource) : AppSettingRepository {
    override val mutableLiveData: MutableLiveData<Boolean> = localSource.showBottomTitlesLiveData

    companion object: SingletonHolder<AppSettingRepositoryImpl, Context>({ context ->
        AppSettingRepositoryImpl(AppSettingLocalSourceImpl.instance(context))
    })
}