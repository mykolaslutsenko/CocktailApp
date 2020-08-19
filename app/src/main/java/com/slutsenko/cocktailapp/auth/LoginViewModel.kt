package com.slutsenko.cocktailapp.auth


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel

class LoginViewModel(application: Application,
                     private val cocktailRepository: CocktailRepository,
                     private val mapper: CocktailModelMapper,
                     savedStateHandle: SavedStateHandle
        )
    : BaseViewModel(application) {

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

    val isLoginDataCorrectLiveData: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        fun check() {
            value = isValidLogin(loginInputLiveData.value) && isValidPassword(passwordInputLiveData.value)
        }
        addSource(loginInputLiveData) {
            check()
        }
        addSource(passwordInputLiveData) {
            check()
        }
    }


    private fun isValidLogin(login: String?): Boolean {
        return login?.length!! >= 6
    }


    private fun isValidPassword(password: String?): Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,}$"
            val passwordMatcher = Regex(passwordPattern)
            return passwordMatcher.find(password) != null
        } ?: return false
    }

    init {
        loginInputLiveData.value = "mykola"
        passwordInputLiveData.value = "a23456"
    }
}

