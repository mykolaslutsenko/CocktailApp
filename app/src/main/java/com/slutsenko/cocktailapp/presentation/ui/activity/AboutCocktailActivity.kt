package com.slutsenko.cocktailapp.presentation.ui.activity

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.ActivityAboutCocktailBinding
import com.slutsenko.cocktailapp.presentation.adapter.list.IngredientOrMeasureAdapter
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailModel
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.viewmodel.AboutCocktailViewModel
import kotlinx.android.synthetic.main.activity_about_cocktail.*
import kotlin.reflect.KClass

class AboutCocktailActivity : BaseActivity<AboutCocktailViewModel, ActivityAboutCocktailBinding>() {

    override val viewModelClass: KClass<AboutCocktailViewModel>
        get() = AboutCocktailViewModel::class

    //override val viewModel: AboutCocktailViewModel by viewModels()

    //lateinit var cocktail: Cocktail

    lateinit var cocktailAdapter: IngredientOrMeasureAdapter

    override fun myView(): Int {
        return R.layout.activity_about_cocktail
    }

    override fun activityCreated() {



        abl_login.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

        })
        val current = intent.getSerializableExtra("cocktail") as CocktailModel
        viewModel.currentCocktailLiveData?.value = current
        viewModel.saveToDb(current)

        cocktailAdapter = IngredientOrMeasureAdapter(this, viewModel.currentCocktailLiveData?.value!!.ingredients, viewModel.currentCocktailLiveData?.value!!.measures)
        rv_ingredient_and_measure.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_ingredient_and_measure.adapter = cocktailAdapter

        //viewModel.cocktailLiveData.value = intent.getSerializableExtra("cocktail") as CocktailNetModel
        //viewModel.cocktailLiveData.value = cocktail
        //ctl_collaps.title = cocktail.strDrink
       // title = cocktail.strDrink

//        CocktailDatabase.getInstance(this)?.cocktailDao()?.addCocktail(viewModel.cocktailLiveData.value)
//        mainViewModel.cocktailDBLiveData?.value = CocktailDatabase.getInstance(this)?.cocktailDao()?.cocktails
    }

    override fun configureDataBinding(binding: ActivityAboutCocktailBinding) {
        super.configureDataBinding(binding)
        binding.viewModel = viewModel
    }

    override fun onDestroy() {
        //startService(Intent(this, DrinkService::class.java))
        super.onDestroy()
    }


    fun back(view: View) {
        onBackPressed()
    }
}