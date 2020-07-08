package com.slutsenko.cocktailapp.auth


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.slutsenko.cocktailapp.app.MyApplication
import com.slutsenko.cocktailapp.base.BaseViewModel

class LoginViewModel : BaseViewModel() {

    private val myLogin = "mykola"
    private val myPassword = "a23456"

    val loginInputLiveData: MutableLiveData<String?> = MutableLiveData()
    val passwordInputLiveData: MutableLiveData<String?> = MutableLiveData()
    val isLoginDataValidLiveData: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {

        fun invalidate() {
            value = myLogin == loginInputLiveData.value && myPassword == passwordInputLiveData.value
        }
        addSource(loginInputLiveData) {
            invalidate()
        }
        addSource(passwordInputLiveData) {
            invalidate()
        }
    }
}

