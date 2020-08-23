package com.slutsenko.cocktailapp.data.network.impl.source

import com.slutsenko.cocktailapp.data.network.impl.service.UserApiService
import com.slutsenko.cocktailapp.data.network.impl.source.base.BaseNetSourceImpl
import com.slutsenko.cocktailapp.data.network.model.user.UserNetModel
import com.slutsenko.cocktailapp.data.network.source.UserNetSource

class UserNetSourceImpl(
    apiService: UserApiService
) : BaseNetSourceImpl<UserApiService>(apiService),
        UserNetSource {

    override suspend fun getUser(): UserNetModel {
        return performRequest {
            getUser()
        }
    }

    override suspend fun updateUser(user: UserNetModel) {
        return performRequest {
            updateUser(user)
        }
    }
}
