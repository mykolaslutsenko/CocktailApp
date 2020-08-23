package com.slutsenko.cocktailapp.data.network.impl.service

import com.slutsenko.cocktailapp.data.network.Constant.Header.TOKEN_HEADER
import com.slutsenko.cocktailapp.data.network.model.user.UserNetModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

@JvmSuppressWildcards
interface UserApiService {

    @Headers(TOKEN_HEADER)
    @GET("users/profile")
    suspend fun getUser(): UserNetModel

    @Headers(TOKEN_HEADER)
    @GET("users/profile")
    suspend fun updateUser(@Body user: UserNetModel)
}