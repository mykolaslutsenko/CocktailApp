package com.slutsenko.cocktailapp.auth


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.slutsenko.cocktailapp.data.repository.source.AuthRepository
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel

class LoginViewModel(application: Application,
                     private val cocktailRepository: CocktailRepository,
                     private val authRepository: AuthRepository,
                     private val mapper: CocktailModelMapper,
                     savedStateHandle: SavedStateHandle
) : BaseViewModel(application) {
    var isCorrectLoginWithServerLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var isCorrectRegisterWithServerLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val firstNameInputLiveData: MutableLiveData<String?> = MutableLiveData()
    val lastNameInputLiveData: MutableLiveData<String?> = MutableLiveData()
    val emailInputLiveData: MutableLiveData<String?> = MutableLiveData()
    val passwordInputLiveData: MutableLiveData<String?> = MutableLiveData()
    val repeatPasswordInputLiveData: MutableLiveData<String?> = MutableLiveData()

    init {
        emailInputLiveData.value = "mykola@gmail.com"
        passwordInputLiveData.value = "a23456"
    }
    fun login() {
        if (isLoginDataCorrectLiveData.value == true) {
            launchRequest(isCorrectLoginWithServerLiveData) {
                authRepository.signIn(
                        emailInputLiveData.value ?: "",
                        passwordInputLiveData.value ?: ""
                )
            }
        } else {
            isCorrectLoginWithServerLiveData.value = false
        }
    }

    fun register() {
        if (isRegisterDataCorrectLiveData.value == true) {
            launchRequest(isCorrectRegisterWithServerLiveData) {
                authRepository.signUp(
                        firstNameInputLiveData.value ?: "",
                        lastNameInputLiveData.value ?: "",
                        emailInputLiveData.value ?: "",
                        passwordInputLiveData.value ?: ""
                )
            }
        } else {
            isCorrectRegisterWithServerLiveData.value = false
        }
    }

    val isLoginDataCorrectLiveData: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        fun check() {
            value = isValidLogin(emailInputLiveData.value) &&
                    isValidPassword(passwordInputLiveData.value)
        }
        addSource(emailInputLiveData) {
            check()
        }
        addSource(passwordInputLiveData) {
            check()
        }
    }

    val isRegisterDataCorrectLiveData: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        fun check() {
            value = isValidLogin(emailInputLiveData.value) &&
                    isValidPassword(passwordInputLiveData.value) &&
                    passwordInputLiveData.value == repeatPasswordInputLiveData.value
        }
        addSource(firstNameInputLiveData) {
            check()
        }
        addSource(lastNameInputLiveData) {
            check()
        }
        addSource(emailInputLiveData) {
            check()
        }
        addSource(passwordInputLiveData) {
            check()
        }
        addSource(repeatPasswordInputLiveData) {
            check()
        }
    }

    private fun isValidLogin(login: String?): Boolean {
        return login?.length ?: 0 >= 6
    }


    private fun isValidPassword(password: String?): Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,}$"
            val passwordMatcher = Regex(passwordPattern)
            return passwordMatcher.find(password) != null
        } ?: return false
    }
}