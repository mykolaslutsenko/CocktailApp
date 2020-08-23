package com.slutsenko.cocktailapp.data.network.impl.source

import com.google.gson.JsonObject
import com.slutsenko.cocktailapp.data.network.impl.service.AuthApiService
import com.slutsenko.cocktailapp.data.network.impl.source.base.BaseNetSourceImpl
import com.slutsenko.cocktailapp.data.network.source.AuthNetSource

class AuthNetSourceImpl(
        apiService: AuthApiService
) : BaseNetSourceImpl<AuthApiService>(apiService),
        AuthNetSource {

    override suspend fun signIn(email: String, password: String): String {
        return performRequest {
            signIn(
                JsonObject().apply {
                    addProperty("email", email)
                    addProperty("password", password)
                }
            ).token
        }
    }

    override suspend fun signUp(
        name: String,
        lastName: String,
        email: String,
        password: String
    ): String {
        return performRequest {
            signUp(
                JsonObject().apply {
                    addProperty("name", name)
                    addProperty("lastName", lastName)
                    addProperty("email", email)
                    addProperty("password", password)
                }
            ).token
        }
    }
}
