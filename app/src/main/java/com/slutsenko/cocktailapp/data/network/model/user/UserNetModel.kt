package com.slutsenko.cocktailapp.data.network.model.user

import com.google.gson.annotations.SerializedName

data class UserNetModel(
    @SerializedName("id")
    val id: Long = 1L,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("lastName")
    val lastName: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("avatar")
    val avatar: String?
)