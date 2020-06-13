package com.slutsenko.cocktailapp.ui

import com.bumptech.glide.Glide
import com.slutsenko.cocktailapp.Base
import com.slutsenko.cocktailapp.Cocktail
import com.slutsenko.cocktailapp.R
import kotlinx.android.synthetic.main.activity_about_cocktail.*

class AboutCocktailActivity : Base() {
    lateinit var cocktail: Cocktail

    override fun myView(): Int {
        return R.layout.activity_about_cocktail
    }

    override fun activityCreated() {
        cocktail = intent.getSerializableExtra("cocktail") as Cocktail
        title = cocktail.strDrink
        customizeComponents()
        MainActivity.cocktailDatabase?.cocktailDao()?.addCocktail(cocktail)
    }

    private fun customizeComponents() {
        val strInfoItem = """
            Name:
            Alcoholic:
            Glass:
            """.trimIndent()
        val strInfoValue = """
            ${cocktail.strDrink}
            ${cocktail.strAlcoholic}
            ${cocktail.strGlass}
            """.trimIndent()
        val strIngredientTitle = "Ingredients"
        val strInstructionsTitle = "Instructions"

        Glide.with(this).load(cocktail.strDrinkThumb).into(iv_cocktail_full)
        tv_info_item.text = strInfoItem
        tv_info_value.text = strInfoValue
        tv_ingredient_title.text = strIngredientTitle
        tv_ingredient_names.text = cocktail.ingredients
        tv_ingredient_values.text = cocktail.measures
        tv_instructions_title.text = strInstructionsTitle
        tv_instructions_value.text = cocktail.strInstructions
    }
}