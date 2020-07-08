package com.slutsenko.cocktailapp.viewmodel

import android.view.MenuItem
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.app.MyApplication
import com.slutsenko.cocktailapp.base.BaseViewModel
import com.slutsenko.cocktailapp.entity.Cocktail
import com.slutsenko.cocktailapp.filter.AlcoholDrinkFilter
import com.slutsenko.cocktailapp.filter.CategoryDrinkFilter

class MainViewModel : BaseViewModel() {

    val showNavigationBarTitlesLiveData: MutableLiveData<Boolean> = MutableLiveData()

    val sortCocktailLiveData: MutableLiveData<List<Cocktail>> = MutableLiveData()

    val alcoholDrinkFilterLiveData: MutableLiveData<AlcoholDrinkFilter> = MutableLiveData()

    var categoryDrinkFilterLiveData: MutableLiveData<CategoryDrinkFilter> = MutableLiveData()

    var cocktailQuantityLiveData: MutableLiveData<Int> = MutableLiveData()

    var cocktailDBLiveData: MutableLiveData<List<Cocktail>>? = MutableLiveData()

    var allDrinkFiltersLiveData: MutableLiveData<Any> = MediatorLiveData<Any>().apply {
        addSource(alcoholDrinkFilterLiveData) {
            value = alcoholDrinkFilterLiveData.value
        }
        addSource(categoryDrinkFilterLiveData) {
            value = categoryDrinkFilterLiveData.value
        }
    }

    fun sortCocktailList(menuItem: MenuItem): List<Cocktail> {
        when (menuItem.itemId) {
            R.id.menu_sort_recent -> {
                sortCocktailLiveData.value = sortCocktailLiveData.value?.sortedBy { it.dateModified }
            }
            R.id.menu_sort_nameAscending -> {
                sortCocktailLiveData.value = sortCocktailLiveData.value?.sortedBy { it.strDrink }

            }
            R.id.menu_sort_nameDescending -> {
                sortCocktailLiveData.value = sortCocktailLiveData.value?.sortedByDescending { it.strDrink }

            }
            R.id.menu_sort_alcoholFirst -> {
                sortCocktailLiveData.value = sortCocktailLiveData.value?.sortedByDescending { it.strDrink }

            }
            R.id.menu_sort_nonAlcoholFirst -> {
                sortCocktailLiveData.value = sortCocktailLiveData.value?.sortedByDescending { it.strDrink }

            }
            R.id.menu_sort_ingredientAscending -> {
                sortCocktailLiveData.value = sortCocktailLiveData.value?.sortedBy { it.ingredients }

            }
            R.id.menu_sort_ingredientDescending -> {
                sortCocktailLiveData.value = sortCocktailLiveData.value?.sortedByDescending { it.ingredients }
            }
            //else -> return super.onContextItemSelected(item)

        }
        return sortCocktailLiveData.value!!
    }

    fun setAlcoholFilters(menuItem: MenuItem): String {
        when (menuItem.itemId) {
            R.id.item_alcoholic -> {
                alcoholDrinkFilterLiveData.value = AlcoholDrinkFilter.ALCOHOLIC
            }
            R.id.item_nonAlcoholic -> {
                alcoholDrinkFilterLiveData.value = AlcoholDrinkFilter.NON_ALCOHOLIC
            }
            R.id.item_optionalAlcohol -> {
                alcoholDrinkFilterLiveData.value = AlcoholDrinkFilter.OPTIONAL_ALCOHOL
            }
        }
        return alcoholDrinkFilterLiveData.value!!.key
    }

    fun setCategoryFilters(menuItem: MenuItem): String {

        return ""

    }


    fun filterAlcohol(alcoholDrinkFilter: AlcoholDrinkFilter): List<Cocktail> {
        var filteredList: List<Cocktail>? = cocktailDBLiveData?.value!!
        filteredList = when (alcoholDrinkFilter) {
            AlcoholDrinkFilter.ALCOHOLIC -> filteredList?.filter {
                it.strAlcoholic == AlcoholDrinkFilter.ALCOHOLIC.key
            }
            AlcoholDrinkFilter.NON_ALCOHOLIC -> filteredList?.filter {
                it.strAlcoholic == AlcoholDrinkFilter.NON_ALCOHOLIC.key
            }
            AlcoholDrinkFilter.OPTIONAL_ALCOHOL -> filteredList?.filter {
                it.strAlcoholic == AlcoholDrinkFilter.OPTIONAL_ALCOHOL.key
            }
            AlcoholDrinkFilter.NON -> cocktailDBLiveData?.value
        }
        return filteredList!!
    }


//    fun filterCocktailList(alcoholDrinkFilter: AlcoholDrinkFilter, categoryDrinkFilter: CategoryDrinkFilter): List<Cocktail> {
//        filterAlcohol(alcoholDrinkFilter)
//        filterCategory(categoryDrinkFilter)
//        return filteredList!!
//    }


//    private fun filterCategory(categoryDrinkFilter: CategoryDrinkFilter) {
//        filteredList = when (categoryDrinkFilter) {
//            CategoryDrinkFilter.ORDINARY_DRINK -> filteredList?.filter {
//                it.strCategory == CategoryDrinkFilter.ORDINARY_DRINK.key
//            }
//            CategoryDrinkFilter.COCKTAIL -> filteredList?.filter {
//                it.strCategory == CategoryDrinkFilter.COCKTAIL.key
//            }
//            CategoryDrinkFilter.MILK_FLOAT_SHAKE -> filteredList?.filter {
//                it.strCategory == CategoryDrinkFilter.MILK_FLOAT_SHAKE.key
//            }
//            CategoryDrinkFilter.OTHER_UNKNOWN -> filteredList?.filter {
//                it.strCategory == CategoryDrinkFilter.OTHER_UNKNOWN.key
//            }
//            CategoryDrinkFilter.COCOA -> filteredList?.filter {
//                it.strCategory == CategoryDrinkFilter.COCOA.key
//            }
//            CategoryDrinkFilter.SHOT -> filteredList?.filter {
//                it.strCategory == CategoryDrinkFilter.SHOT.key
//            }
//            CategoryDrinkFilter.COFFEE_TEA -> filteredList?.filter {
//                it.strCategory == CategoryDrinkFilter.COFFEE_TEA.key
//            }
//            CategoryDrinkFilter.HOMEMADE_LIQUEUR -> filteredList?.filter {
//                it.strCategory == CategoryDrinkFilter.HOMEMADE_LIQUEUR.key
//            }
//            CategoryDrinkFilter.PUNCH_PARTY_DRINK -> filteredList?.filter {
//                it.strCategory == CategoryDrinkFilter.PUNCH_PARTY_DRINK.key
//            }
//            CategoryDrinkFilter.BEER -> filteredList?.filter {
//                it.strCategory == CategoryDrinkFilter.BEER.key
//            }
//            CategoryDrinkFilter.SOFT_DRINK_SODA -> filteredList?.filter {
//                it.strCategory == CategoryDrinkFilter.SOFT_DRINK_SODA.key
//            }
//            CategoryDrinkFilter.NON -> filteredList
//        }
//    }
}