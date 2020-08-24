package com.slutsenko.cocktailapp.data.repository.impl.source

import androidx.lifecycle.LiveData
import com.slutsenko.cocktailapp.data.lokal.source.TokenLocalSource
import com.slutsenko.cocktailapp.data.repository.impl.source.base.BaseRepositoryImpl
import com.slutsenko.cocktailapp.data.repository.source.TokenRepository

class TokenRepositoryImpl(
        private val localSource: TokenLocalSource
) : BaseRepositoryImpl(), TokenRepository {
    override val tokenLiveData: LiveData<String?> = localSource.tokenLiveData
    override var token: String?
        get() = localSource.token
        set(value) { localSource.token = value }

}
