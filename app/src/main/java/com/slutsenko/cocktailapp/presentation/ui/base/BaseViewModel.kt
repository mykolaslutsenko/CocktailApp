package com.slutsenko.cocktailapp.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel() : ViewModel() {

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