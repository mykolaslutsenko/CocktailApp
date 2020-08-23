package com.slutsenko.cocktailapp.data.network.impl.service

import com.google.gson.JsonObject
import com.slutsenko.cocktailapp.data.network.model.responce.TokenNetModel
import retrofit2.http.Body
import retrofit2.http.POST

@JvmSuppressWildcards
interface AuthApiService {

    @POST("login")
    suspend fun signIn(@Body jsonObject: JsonObject): TokenNetModel

    @POST("register")
    suspend fun signUp(@Body jsonObject: JsonObject): TokenNetModel
}