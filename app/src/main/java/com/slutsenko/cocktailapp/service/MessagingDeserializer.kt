package com.slutsenko.cocktailapp.service

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.slutsenko.cocktailapp.data.network.impl.extension.getIfHasMember
import java.lang.reflect.Type

class MessagingDeserializer : JsonDeserializer<MessagingModel> {

    @Throws(JsonParseException::class)
    override fun deserialize(
            json: JsonElement?, typeOfT: Type,
            context: JsonDeserializationContext
    ): MessagingModel {
        return json!!.asJsonObject!!.let { jsonObject ->
            MessagingModel(
                    id = jsonObject.getIfHasMember("id")?.asLong ?: 1L,
                    title = jsonObject.getIfHasMember("title")?.asString ?: "",
                    body = jsonObject.getIfHasMember("body")?.asString ?: "",
                    type = MessagingType.values()
                            .firstOrNull { it.value == jsonObject.getIfHasMember("type")?.asString }
                            ?: MessagingType.UNDEFINED,
                    image = jsonObject.getIfHasMember("image")?.asString ?: "",
                    cocktailId = jsonObject.getIfHasMember("cocktail_id")?.asLong ?: 1L
            )
        }
    }
}