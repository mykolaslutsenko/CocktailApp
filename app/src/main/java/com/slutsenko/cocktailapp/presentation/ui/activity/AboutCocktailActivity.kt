package com.slutsenko.cocktailapp.presentation.ui.activity

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.ActivityAboutCocktailBinding
import com.slutsenko.cocktailapp.presentation.adapter.list.IngredientWithMeasureAdapter
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailModel
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.viewmodel.AboutCocktailViewModel
import kotlinx.android.synthetic.main.activity_about_cocktail.*
import kotlin.reflect.KClass

class AboutCocktailActivity : BaseActivity<AboutCocktailViewModel, ActivityAboutCocktailBinding>() {

    override val viewModelClass: KClass<AboutCocktailViewModel>
        get() = AboutCocktailViewModel::class

    lateinit var cocktailAdapter: IngredientWithMeasureAdapter

    override fun myView(): Int {
        return R.layout.activity_about_cocktail
    }

    override fun activityCreated() {
        abl_login.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset -> })
        val current = intent.getSerializableExtra("cocktail") as CocktailModel
        viewModel.currentCocktailLiveData?.value = current
        viewModel.saveToDb(current)

        cocktailAdapter = IngredientWithMeasureAdapter(this, viewModel.currentCocktailLiveData?.value!!.ingredients, viewModel.currentCocktailLiveData?.value!!.measures)
        rv_ingredient_and_measure.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_ingredient_and_measure.adapter = cocktailAdapter
    }

    override fun configureDataBinding(binding: ActivityAboutCocktailBinding) {
        super.configureDataBinding(binding)
        binding.viewModel = viewModel
    }

    fun back(view: View) {
        onBackPressed()
    }
}