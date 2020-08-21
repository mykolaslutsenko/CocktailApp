package com.slutsenko.cocktailapp.data.network.model.cocktail

import java.io.Serializable
import java.util.*


class CocktailNetModel(val id: Long = -1L,
                       val names: LocalizedStringNetModel = LocalizedStringNetModel(),
                       val category: String = "",
                       val alcoholType: String = "",
                       val glass: String = "",
                       val image: String = "",
                       val instructions: LocalizedStringNetModel = LocalizedStringNetModel(),
                       val ingredientsWithMeasures: Map<String, String> = emptyMap(),
                       val date: Date = Date()) : Serializable {
//    @SerializedName("idDrink")
//    @Expose
//    var id: Long? = null
//
//    @SerializedName("strDrink")
//    @Expose
//    val names: LocalizedStringNetModel = LocalizedStringNetModel()
//
//    @SerializedName("strDrinkAlternate")
//    @Expose
//    var strDrinkAlternate: String? = null
//
//    @SerializedName("strDrinkES")
//    @Expose
//    var strDrinkES: String? = null
//
//    @SerializedName("strDrinkDE")
//    @Expose
//    var strDrinkDE: String? = null
//
//    @SerializedName("strDrinkFR")
//    @Expose
//    var strDrinkFR: String? = null
//
//    @SerializedName("strDrinkZH-HANS")
//    @Expose
//    var strDrinkZHHANS: String? = null
//
//    @SerializedName("strDrinkZH-HANT")
//    @Expose
//    var strDrinkZHHANT: String? = null
//
//    @SerializedName("strTags")
//    @Expose
//    var strTags: String? = null
//
//    @SerializedName("strVideo")
//    @Expose
//    var strVideo: String? = null
//
//    @SerializedName("strCategory")
//    @Expose
//    var category: String? = null
//
//    @SerializedName("strIBA")
//    @Expose
//    var strIBA: String? = null
//
//    @SerializedName("strAlcoholic")
//    @Expose
//    var alcoholType: String? = null
//
//    @SerializedName("strGlass")
//    @Expose
//    var glass: String? = null
//
//    @SerializedName("strInstructions")
//    @Expose
//    val instructions: LocalizedStringNetModel = LocalizedStringNetModel()
//
//    @SerializedName("strInstructionsES")
//    @Expose
//    var strInstructionsES: String? = null
//
//    @SerializedName("strInstructionsDE")
//    @Expose
//    var strInstructionsDE: String? = null
//
//    @SerializedName("strInstructionsFR")
//    @Expose
//    var strInstructionsFR: String? = null
//
//    @SerializedName("strInstructionsZH-HANS")
//    @Expose
//    var strInstructionsZHHANS: String? = null
//
//    @SerializedName("strInstructionsZH-HANT")
//    @Expose
//    var strInstructionsZHHANT: String? = null
//
//    @SerializedName("strDrinkThumb")
//    @Expose
//    var image: String? = null
//
//    @SerializedName("strIngredient1")
//    @Expose
//    var strIngredient1: String? = null
//
//    @SerializedName("strIngredient2")
//    @Expose
//    var strIngredient2: String? = null
//
//    @SerializedName("strIngredient3")
//    @Expose
//    var strIngredient3: String? = null
//
//    @SerializedName("strIngredient4")
//    @Expose
//    var strIngredient4: String? = null
//
//    @SerializedName("strIngredient5")
//    @Expose
//    var strIngredient5: String? = null
//
//    @SerializedName("strIngredient6")
//    @Expose
//    var strIngredient6: String? = null
//
//    @SerializedName("strIngredient7")
//    @Expose
//    var strIngredient7: String? = null
//
//    @SerializedName("strIngredient8")
//    @Expose
//    var strIngredient8: String? = null
//
//    @SerializedName("strIngredient9")
//    @Expose
//    var strIngredient9: String? = null
//
//    @SerializedName("strIngredient10")
//    @Expose
//    var strIngredient10: String? = null
//
//    @SerializedName("strIngredient11")
//    @Expose
//    var strIngredient11: String? = null
//
//    @SerializedName("strIngredient12")
//    @Expose
//    var strIngredient12: String? = null
//
//    @SerializedName("strIngredient13")
//    @Expose
//    var strIngredient13: String? = null
//
//    @SerializedName("strIngredient14")
//    @Expose
//    var strIngredient14: String? = null
//
//    @SerializedName("strIngredient15")
//    @Expose
//    var strIngredient15: String? = null
//
//    @SerializedName("strMeasure1")
//    @Expose
//    var strMeasure1: String? = null
//
//    @SerializedName("strMeasure2")
//    @Expose
//    var strMeasure2: String? = null
//
//    @SerializedName("strMeasure3")
//    @Expose
//    var strMeasure3: String? = null
//
//    @SerializedName("strMeasure4")
//    @Expose
//    var strMeasure4: String? = null
//
//    @SerializedName("strMeasure5")
//    @Expose
//    var strMeasure5: String? = null
//
//    @SerializedName("strMeasure6")
//    @Expose
//    var strMeasure6: String? = null
//
//    @SerializedName("strMeasure7")
//    @Expose
//    var strMeasure7: String? = null
//
//    @SerializedName("strMeasure8")
//    @Expose
//    var strMeasure8: String? = null
//
//    @SerializedName("strMeasure9")
//    @Expose
//    var strMeasure9: String? = null
//
//    @SerializedName("strMeasure10")
//    @Expose
//    var strMeasure10: String? = null
//
//    @SerializedName("strMeasure11")
//    @Expose
//    var strMeasure11: String? = null
//
//    @SerializedName("strMeasure12")
//    @Expose
//    var strMeasure12: String? = null
//
//    @SerializedName("strMeasure13")
//    @Expose
//    var strMeasure13: String? = null
//
//    @SerializedName("strMeasure14")
//    @Expose
//    var strMeasure14: String? = null
//
//    @SerializedName("strMeasure15")
//    @Expose
//    var strMeasure15: String? = null
//
//    @SerializedName("strCreativeCommonsConfirmed")
//    @Expose
//    var strCreativeCommonsConfirmed: String? = null
//
//    @SerializedName("dateModified")
//    @Expose
//    var dateModified: String? = null
//
//    var ingredients = listOf<String>()
//
//    var measures = listOf<String>()
}