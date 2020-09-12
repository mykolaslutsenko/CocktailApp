package com.slutsenko.cocktailapp.feature.detail

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.ActivityAboutCocktailBinding
import com.slutsenko.cocktailapp.presentation.adapter.list.IngredientWithMeasureAdapter
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
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

        val idNotification = intent.getSerializableExtra("cocktail_id_notification") as? Long
        val idAdapter = intent.getSerializableExtra("cocktail_id_adapter") as? Long

        val id = idAdapter ?: idNotification

        viewModel.currentCocktailLiveData = viewModel.searchCocktail(id!!)

        viewModel.currentCocktailLiveData.observe {
            cocktailAdapter.refreshAdapter(
                    viewModel.currentCocktailLiveData.value?.ingredients,
                    viewModel.currentCocktailLiveData.value?.measures
            )
        }

        cocktailAdapter = IngredientWithMeasureAdapter(
                this,
                viewModel.currentCocktailLiveData.value?.ingredients,
                viewModel.currentCocktailLiveData.value?.measures)
        rv_ingredient_and_measure.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_ingredient_and_measure.adapter = cocktailAdapter
        //rv_ingredient_and_measure.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
    }

    override fun configureDataBinding(binding: ActivityAboutCocktailBinding) {
        super.configureDataBinding(binding)
        binding.viewModel = viewModel
    }

    fun back(view: View) {
        onBackPressed()
    }
}