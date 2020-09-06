package com.slutsenko.cocktailapp.service

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MessagingModel(
        @SerializedName("id")
        val id: Long,
        @SerializedName("body")
        val body: String,
        @SerializedName("type")
        val type: MessagingType,
        @SerializedName("title")
        val title: String,
        @SerializedName("cocktail_id")
        val cocktailId: Long?,
        @SerializedName("cocktail_url")
        val image: String?
):Serializable

