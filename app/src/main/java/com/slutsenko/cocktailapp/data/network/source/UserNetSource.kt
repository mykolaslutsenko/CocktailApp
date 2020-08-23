package com.slutsenko.cocktailapp.data.network.source

import com.slutsenko.cocktailapp.data.network.model.user.UserNetModel
import com.slutsenko.cocktailapp.data.network.source.base.BaseNetSource


interface UserNetSource: BaseNetSource {
    suspend fun getUser(): UserNetModel
    suspend fun updateUser(user: UserNetModel)
}