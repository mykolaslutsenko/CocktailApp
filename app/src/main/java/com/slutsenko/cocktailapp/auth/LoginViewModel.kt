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
    var isLoggedLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loginInputLiveData: MutableLiveData<String?> = MutableLiveData()
    val passwordInputLiveData: MutableLiveData<String?> = MutableLiveData()

    fun invalidate() {
        launchRequest(isLoggedLiveData) {
            authRepository.signIn(
                    email = loginInputLiveData.value!!,
                    password = passwordInputLiveData.value!!
            )
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

    fun register() {
        launchRequest (isLoggedLiveData) {
            authRepository.signUp(
                    "ololo12",
                    "oloko12",
                    "olo1l2o@gmail.com",
                    "ol2o1123"
            )
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

}

