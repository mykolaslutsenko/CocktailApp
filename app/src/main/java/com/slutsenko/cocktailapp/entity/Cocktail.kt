package com.slutsenko.cocktailapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "cocktail")
class Cocktail : Serializable {
    var idDrink: String? = null

    @PrimaryKey
    var strDrink = ""



    var isFavorite:Boolean? = false
    var strDrinkAlternate: String? = null
    var strDrinkES: String? = null
    var strDrinkDE: String? = null
    var strDrinkFR: String? = null

    @SerializedName("strDrinkZH-HANS")
    var strDrinkZHHANS //strDrinkZH-HANS
            : String? = null

    @SerializedName("strDrinkZH-HANT")
    var strDrinkZHHANT //strDrinkZH-HANT
            : String? = null
    var strTags: String? = null
    var strVideo: String? = null
    var strCategory: String? = null
    var strIBA: String? = null
    var strAlcoholic: String? = null
    var strGlass: String? = null
    var strInstructions: String? = null
    var strInstructionsES: String? = null
    var strInstructionsDE: String? = null
    var strInstructionsFR: String? = null

    @SerializedName("strInstructionsZH-HANS")
    var strInstructionsZHHANS: String? = null

    @SerializedName("strInstructionsZH-HANT")
    var strInstructionsZHHANT: String? = null
    var strDrinkThumb: String? = null
    var strIngredient1: String? = null
    var strIngredient2: String? = null
    var strIngredient3: String? = null
    var strIngredient4: String? = null
    var strIngredient5: String? = null
    var strIngredient6: String? = null
    var strIngredient7: String? = null
    var strIngredient8: String? = null
    var strIngredient9: String? = null
    var strIngredient10: String? = null
    var strIngredient11: String? = null
    var strIngredient12: String? = null
    var strIngredient13: String? = null
    var strIngredient14: String? = null
    var strIngredient15: String? = null
    var strMeasure1: String? = null
    var strMeasure2: String? = null
    var strMeasure3: String? = null
    var strMeasure4: String? = null
    var strMeasure5: String? = null
    var strMeasure6: String? = null
    var strMeasure7: String? = null
    var strMeasure8: String? = null
    var strMeasure9: String? = null
    var strMeasure10: String? = null
    var strMeasure11: String? = null
    var strMeasure12: String? = null
    var strMeasure13: String? = null
    var strMeasure14: String? = null
    var strMeasure15: String? = null
    var strCreativeCommonsConfirmed: String? = null
    var dateModified: String? = null
//    val ingredients = arrayOf(
//                    strIngredient1, strIngredient2, strIngredient3,
//                    strIngredient4, strIngredient5, strIngredient6,
//                    strIngredient7, strIngredient8, strIngredient9,
//                    strIngredient10, strIngredient11, strIngredient12,
//                    strIngredient13, strIngredient14, strIngredient15)
//
//
//    val measures = arrayOf(
//                    strMeasure1, strMeasure2, strMeasure3,
//                    strMeasure4, strMeasure5, strMeasure6,
//                    strMeasure7, strMeasure8, strMeasure9,
//                    strMeasure10, strMeasure11, strMeasure12,
//                    strMeasure13, strMeasure14, strMeasure15)


    //val ingredients: List<String> = emptyList(),
    //val measures: List<String> = emptyList()/*,

}