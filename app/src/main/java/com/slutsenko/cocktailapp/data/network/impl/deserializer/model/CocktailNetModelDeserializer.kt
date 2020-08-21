package com.slutsenko.cocktailapp.data.network.impl.deserializer.model

import com.google.gson.*
import com.slutsenko.cocktailapp.data.network.impl.extension.deserializeType
import com.slutsenko.cocktailapp.data.network.impl.extension.getIfHasMember
import com.slutsenko.cocktailapp.data.network.impl.extension.getMemberStringOrEmpty
import com.slutsenko.cocktailapp.data.network.model.cocktail.CocktailNetModel
import com.slutsenko.cocktailapp.data.network.model.cocktail.LocalizedStringNetModel
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.HashMap

class CocktailNetModelDeserializer : JsonDeserializer<CocktailNetModel> {

    private val dateType = deserializeType<Date>()

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement?, typeOfT: Type,
        context: JsonDeserializationContext
    ) = json!!.asJsonObject!!.let { jsonObject ->
        CocktailNetModel(
            id = jsonObject.get("idDrink").asLong,
            names = LocalizedStringNetModel(
                def = jsonObject.getMemberStringOrEmpty("strDrink"),
                defAlternate = jsonObject.getMemberStringOrEmpty("strDrinkAlternate"),
                es = jsonObject.getMemberStringOrEmpty("strDrinkES"),
                de = jsonObject.getMemberStringOrEmpty("strDrinkDE"),
                fr = jsonObject.getMemberStringOrEmpty("strDrinkFR"),
                zhHans = jsonObject.getMemberStringOrEmpty("strDrinkZH-HANS"),
                zhHant = jsonObject.getMemberStringOrEmpty("strDrinkZH-HANT")
            ),
            category = jsonObject.getMemberStringOrEmpty("strCategory"),
            alcoholType = jsonObject.getMemberStringOrEmpty("strAlcoholic"),
            glass = jsonObject.getMemberStringOrEmpty("strGlass"),
            image = jsonObject.getMemberStringOrEmpty("strDrinkThumb"),
            instructions = LocalizedStringNetModel(
                def = jsonObject.getMemberStringOrEmpty("strInstructions"),
                defAlternate = jsonObject.getMemberStringOrEmpty("strInstructionsAlternate"),
                es = jsonObject.getMemberStringOrEmpty("strInstructionsES"),
                de = jsonObject.getMemberStringOrEmpty("strInstructionsDE"),
                fr = jsonObject.getMemberStringOrEmpty("strInstructionsFR"),
                zhHans = jsonObject.getMemberStringOrEmpty("strInstructionsZH-HANS"),
                zhHant = jsonObject.getMemberStringOrEmpty("strInstructionsZH-HANT")
            ),
            ingredientsWithMeasures = createIngredientWithMeasure(jsonObject),
            date = when {
                jsonObject.has("dateModified") && !jsonObject.get("dateModified").isJsonNull -> {
                    context.deserialize(jsonObject.get("dateModified"), dateType)
                }
                else -> Date()
            }
        )
    }

    private fun createIngredientWithMeasure(cocktailJsonObject: JsonObject): Map<String, String> {
        val ingredientsWithMeasures = HashMap<String, String>()
        for (i in 1..15) {
            val ingredient =
                    cocktailJsonObject.getIfHasMember("strIngredient${i}")?.asString ?: continue
            val measure =
                    cocktailJsonObject.getIfHasMember("strMeasure${i}")?.asString ?: ""
            ingredientsWithMeasures[ingredient] = measure
        }
        return ingredientsWithMeasures
    }
}
