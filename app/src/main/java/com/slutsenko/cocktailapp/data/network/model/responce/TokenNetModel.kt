package com.slutsenko.cocktailapp.data.network.model.responce

import com.google.gson.annotations.SerializedName

data class TokenNetModel(
    @SerializedName("token")
    val token: String
)