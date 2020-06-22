package com.slutsenko.cocktailapp.ui

import android.content.Intent
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.slutsenko.cocktailapp.Base
import com.slutsenko.cocktailapp.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.service.DrinkService
import com.slutsenko.cocktailapp.ui.fragment.MainFragment
import kotlinx.android.synthetic.main.activity_about_cocktail.*

class AboutCocktailActivity : Base() {
    lateinit var cocktail: Cocktail

    override fun myView(): Int {

        return R.layout.activity_about_cocktail
    }

    override fun activityCreated() {

        abl_login.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            Log.d("ololo", verticalOffset.toString())
        })
        cocktail = intent.getSerializableExtra("cocktail") as Cocktail
        ctl_collaps.title = cocktail.strDrink
       // title = cocktail.strDrink
        customizeComponents()
        MainFragment.cocktailDatabase?.cocktailDao()?.addCocktail(cocktail)
    }

    override fun onDestroy() {
        startService(Intent(this, DrinkService::class.java))
        super.onDestroy()
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
        val strBasic = "Basic information"
        val strIngredientTitle = "Ingredients"
        val strInstructionsTitle = "Instructions"

        Glide.with(this).load(cocktail.strDrinkThumb).into(iv_cocktail_full)
        tv_basic.text = strBasic
        tv_info_item.text = strInfoItem
        tv_info_value.text = strInfoValue
        tv_ingredient_title.text = strIngredientTitle
        tv_ingredient_names.text = cocktail.ingredients
        tv_ingredient_values.text = cocktail.measures
        tv_instructions_title.text = strInstructionsTitle
        tv_instructions_value.text = cocktail.strInstructions
    }

    fun back(view: View) {

    }
}