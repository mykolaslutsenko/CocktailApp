package com.slutsenko.cocktailapp.presentation.ui.activity

import android.view.View
import android.widget.FrameLayout
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

    private var maxImageWidth: Int? = null
    private var minImageWidth: Int? = null
    private var cachedImageWidth: Int? = null
    private var imageMarginStart: Int? = null
    private var imageMarginTop: Int? = null
    private lateinit var imageViewParams: FrameLayout.LayoutParams

    override fun myView(): Int {
        return R.layout.activity_about_cocktail
    }

    override fun activityCreated() {
        abl_login.addOnOffsetChangedListener(
                AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (maxImageWidth == null) {
                initImageParameters()
            }

            val totalsScrollRange = appBarLayout.totalScrollRange
            val offsetFactor = (-verticalOffset).toFloat() / totalsScrollRange
            val scaleFactor = 1F - offsetFactor

            val currentImageWidth = ((maxImageWidth!! - minImageWidth!!) * scaleFactor) + minImageWidth!!

            imageViewParams.marginStart = (imageMarginStart!! * offsetFactor).toInt()
            imageViewParams.topMargin = ((maxImageWidth!! / 2 - imageMarginTop!!) * offsetFactor).toInt()
            imageViewParams.width = currentImageWidth.toInt()

            if (imageViewParams.width != cachedImageWidth) {
                cachedImageWidth = currentImageWidth.toInt()
                iv_cocktail_image.layoutParams = imageViewParams
            }
        })


        val current = intent.getSerializableExtra("cocktail") as CocktailModel
        viewModel.currentCocktailLiveData?.value = current
        viewModel.saveToDb(current)

        cocktailAdapter = IngredientWithMeasureAdapter(
                this,
                viewModel.currentCocktailLiveData?.value!!.ingredients,
                viewModel.currentCocktailLiveData?.value!!.measures)
        rv_ingredient_and_measure.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_ingredient_and_measure.adapter = cocktailAdapter
    }

    private fun initImageParameters() {
        maxImageWidth = iv_cocktail_image.width
        container_image.layoutParams.height = iv_cocktail_image.height
        container_image.requestLayout()
        cachedImageWidth = maxImageWidth

        imageMarginStart = 64
        imageMarginTop = 16
        minImageWidth = 32

        imageViewParams = iv_cocktail_image.layoutParams as FrameLayout.LayoutParams
    }

    override fun configureDataBinding(binding: ActivityAboutCocktailBinding) {
        super.configureDataBinding(binding)
        binding.viewModel = viewModel
    }

    fun back(view: View) {
        onBackPressed()
    }
}