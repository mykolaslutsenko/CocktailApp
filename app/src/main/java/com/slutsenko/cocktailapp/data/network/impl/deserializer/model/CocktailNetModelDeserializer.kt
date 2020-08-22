package com.slutsenko.cocktailapp.data.network.impl.deserializer.model

import com.google.gson.*
import com.slutsenko.cocktailapp.data.network.impl.extension.deserializeType
import com.slutsenko.cocktailapp.data.network.impl.extension.getIfHasMember
import com.slutsenko.cocktailapp.data.network.impl.extension.getMemberStringOrEmpty
import com.slutsenko.cocktailapp.data.network.model.cocktail.CocktailNetModel
import com.slutsenko.cocktailapp.data.network.model.cocktail.LocalizedStringNetModel
import java.lang.reflect.Type
import java.util.*

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
                ingredients = createIngredients(jsonObject),
                measures = createMeasures(jsonObject),
                date = when {
                    jsonObject.has("dateModified") && !jsonObject.get("dateModified").isJsonNull -> {
                        context.deserialize(jsonObject.get("dateModified"), dateType)
                    }
                    else -> Date()
                }
        )
    }

    private fun createMeasures(cocktailJsonObject: JsonObject): List<String> {
        val measureList = mutableListOf<String>()
        for (i in 1..15) {
            val measure =
                    cocktailJsonObject.getIfHasMember("strMeasure${i}")?.asString ?: continue
            measureList.add(measure)
        }
        return measureList
    }

    private fun createIngredients(cocktailJsonObject: JsonObject): List<String> {
        val ingredientList = mutableListOf<String>()
        for (i in 1..15) {
            val ingredient =
                    cocktailJsonObject.getIfHasMember("strIngredient${i}")?.asString ?: continue
            ingredientList.add(ingredient)
        }
        return ingredientList
    }

}
