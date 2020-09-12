package com.slutsenko.cocktailapp.feature.splash

import android.app.Application
import androidx.lifecycle.LiveData
import com.slutsenko.cocktailapp.data.repository.source.TokenRepository
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel

class SplashActivityViewModel(
        application: Application,
        private val tokenRepository: TokenRepository

) : BaseViewModel(application) {

    val tokenLiveData: LiveData<String?> = tokenRepository.tokenLiveData
}