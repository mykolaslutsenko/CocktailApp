package com.slutsenko.cocktailapp.presentation.ui.activity

import android.content.Intent
import android.util.Log
import android.view.View
import com.google.android.material.appbar.AppBarLayout
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.data.network.CocktailNetModel
import com.slutsenko.cocktailapp.databinding.ActivityAboutCocktailBinding
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.viewmodel.AboutCocktailViewModel
import com.slutsenko.cocktailapp.service.DrinkService
import kotlinx.android.synthetic.main.activity_about_cocktail.*
import kotlin.reflect.KClass

class AboutCocktailActivity : BaseActivity<AboutCocktailViewModel, ActivityAboutCocktailBinding>() {

    override val viewModelClass: KClass<AboutCocktailViewModel>
        get() = AboutCocktailViewModel::class

    //lateinit var cocktail: Cocktail

    override fun myView(): Int {
        return R.layout.activity_about_cocktail
    }

    override fun activityCreated() {

        abl_login.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            Log.d("ololo", verticalOffset.toString())
        })
        viewModel.cocktailLiveData.value = intent.getSerializableExtra("cocktail") as CocktailNetModel
        //viewModel.cocktailLiveData.value = cocktail
        //ctl_collaps.title = cocktail.strDrink
       // title = cocktail.strDrink
        customizeComponents()
//        CocktailDatabase.getInstance(this)?.cocktailDao()?.addCocktail(viewModel.cocktailLiveData.value)
//        mainViewModel.cocktailDBLiveData?.value = CocktailDatabase.getInstance(this)?.cocktailDao()?.cocktails
    }

    override fun configureDataBinding(binding: ActivityAboutCocktailBinding) {
        super.configureDataBinding(binding)
        binding.cocktail = viewModel
    }

    override fun onDestroy() {
        startService(Intent(this, DrinkService::class.java))
        super.onDestroy()
    }

    private fun customizeComponents() {
//        val strInfoItem = """
//            Name:
//            Alcoholic:
//            Glass:
//            """.trimIndent()
//        val strInfoValue = """
//            ${cocktail.strDrink}
//            ${cocktail.strAlcoholic}
//            ${cocktail.strGlass}
//            """.trimIndent()
        //val strBasic = "Basic information"
       // val strIngredientTitle = "Ingredients"
       // val strInstructionsTitle = "Instructions"

        //Glide.with(this).load(cocktail.strDrinkThumb).into(iv_cocktail_full)
        //tv_basic.text = strBasic
        //tv_info_item.text = strInfoItem
        //tv_info_value.text = strInfoValue
       // tv_ingredient_title.text = strIngredientTitle
        //tv_ingredient_names.text = cocktail.ingredients
        //tv_ingredient_values.text = cocktail.measures
       // tv_instructions_title.text = strInstructionsTitle
        //tv_instructions_value.text = cocktail.strInstructions
    }

    fun back(view: View) {
        onBackPressed()
    }

//    override val viewModel: AboutCocktailViewModel by viewModels()
//    private val mainViewModel: MainViewModel by viewModels()

}