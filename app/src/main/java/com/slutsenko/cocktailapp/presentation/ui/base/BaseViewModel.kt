package com.slutsenko.cocktailapp.presentation.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.slutsenko.cocktailapp.data.repository.impl.source.AppSettingRepositoryImpl
import com.slutsenko.cocktailapp.data.repository.source.AppSettingRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    protected val appSettingRepository: AppSettingRepository =
            AppSettingRepositoryImpl.instance(application)
    val showNavigationBarTitlesLiveData: MutableLiveData<Boolean> = appSettingRepository.mutableLiveData

    protected fun <T> launchRequest(
            liveData: LiveData<T>? = null,
            context: CoroutineContext = Dispatchers.IO,
            request: suspend CoroutineScope.() -> T
    ): Job {
        return viewModelScope.launch {
            try {
                withContext(context) { request() }.apply { liveData?.setValue(this) }
            } catch (e: Exception) {
                e.printStackTrace()
                throw e
            }
        }
    }


    protected fun <T> LiveData<T>.setValue(value: T) {
        (this as? MutableLiveData)?.value = value
    }

    protected fun <T> LiveData<T>.postValue(value: T) {
        (this as? MutableLiveData)?.postValue(value)
    }


}