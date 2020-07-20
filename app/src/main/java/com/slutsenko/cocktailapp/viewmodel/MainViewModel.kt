package com.slutsenko.cocktailapp.viewmodel

import android.graphics.Color
import android.view.MenuItem
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.base.BaseViewModel
import com.slutsenko.cocktailapp.entity.Cocktail
import com.slutsenko.cocktailapp.filter.AlcoholDrinkFilter
import com.slutsenko.cocktailapp.filter.CategoryDrinkFilter
import com.slutsenko.cocktailapp.filter.DrinkFilterType
import com.slutsenko.cocktailapp.filter.SortDrink

class MainViewModel : BaseViewModel() {

    var filteredList: List<Cocktail>? = null

    var cocktailDBLiveData: MutableLiveData<List<Cocktail>>? = MutableLiveData()

    val alcoholDrinkFilterLiveData: MutableLiveData<AlcoholDrinkFilter> = MutableLiveData()

    var categoryDrinkFilterLiveData: MutableLiveData<CategoryDrinkFilter> = MutableLiveData()

    var historyLiveData: MutableLiveData<List<Cocktail>> = MutableLiveData()

    var favoriteLiveData: MutableLiveData<List<Cocktail>> = MutableLiveData()

    val sortDrinkLiveData: MutableLiveData<SortDrink> = MutableLiveData()

    val showNavigationBarTitlesLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var cocktailQuantityLiveData: MutableLiveData<Int> = MutableLiveData()

    var mediatorLiveData: MutableLiveData<List<Cocktail>> = MediatorLiveData<List<Cocktail>>().apply {
        fun filter() {
            value = filterAndSortCocktailList()
        }
        addSource(alcoholDrinkFilterLiveData) {
            filter()
        }
        addSource(categoryDrinkFilterLiveData) {
            filter()
        }
        addSource(sortDrinkLiveData) {
            filter()
        }
    }

    private fun filterAndSortCocktailList(): List<Cocktail> {
        filteredList = cocktailDBLiveData?.value!!
        filterAlcohol(alcoholDrinkFilterLiveData.value!!)
        filterCategory(categoryDrinkFilterLiveData.value!!)
        sortCocktailList(sortDrinkLiveData.value!!)
        return filteredList!!
    }

    private fun sortCocktailList(sort: SortDrink) {
        filteredList = when (sort) {
            SortDrink.RECENT -> {
                filteredList?.sortedBy { it.dateModified }
            }
            SortDrink.NAME_ASCENDING -> {
                filteredList?.sortedBy { it.strDrink }
            }
            SortDrink.NAME_DESCENDING -> {
                filteredList?.sortedByDescending { it.strDrink }
            }
            SortDrink.ALCOHOL_FIRST -> {
                filteredList?.sortedBy { it.strDrink }
            }
            SortDrink.NON_ALCOHOL_FIRST -> {
                filteredList?.sortedBy { it.strDrink }
            }
            SortDrink.INGREDIENT_ASCENDING -> {
                filteredList?.sortedBy { it.strDrink }
            }
            SortDrink.INGREDIENT_DESCENDING -> {
                filteredList?.sortedByDescending { it.strDrink }
            }
        }
    }

    private fun filterAlcohol(alcoholDrinkFilter: AlcoholDrinkFilter) {
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
            AlcoholDrinkFilter.NONE -> filteredList
        }
    }

    private fun filterCategory(categoryDrinkFilter: CategoryDrinkFilter) {
        filteredList = when (categoryDrinkFilter) {
            CategoryDrinkFilter.ORDINARY_DRINK -> filteredList?.filter {
                it.strCategory == CategoryDrinkFilter.ORDINARY_DRINK.key
            }
            CategoryDrinkFilter.COCKTAIL -> filteredList?.filter {
                it.strCategory == CategoryDrinkFilter.COCKTAIL.key
            }
            CategoryDrinkFilter.MILK_FLOAT_SHAKE -> filteredList?.filter {
                it.strCategory == CategoryDrinkFilter.MILK_FLOAT_SHAKE.key
            }
            CategoryDrinkFilter.OTHER_UNKNOWN -> filteredList?.filter {
                it.strCategory == CategoryDrinkFilter.OTHER_UNKNOWN.key
            }
            CategoryDrinkFilter.COCOA -> filteredList?.filter {
                it.strCategory == CategoryDrinkFilter.COCOA.key
            }
            CategoryDrinkFilter.SHOT -> filteredList?.filter {
                it.strCategory == CategoryDrinkFilter.SHOT.key
            }
            CategoryDrinkFilter.COFFEE_TEA -> filteredList?.filter {
                it.strCategory == CategoryDrinkFilter.COFFEE_TEA.key
            }
            CategoryDrinkFilter.HOMEMADE_LIQUEUR -> filteredList?.filter {
                it.strCategory == CategoryDrinkFilter.HOMEMADE_LIQUEUR.key
            }
            CategoryDrinkFilter.PUNCH_PARTY_DRINK -> filteredList?.filter {
                it.strCategory == CategoryDrinkFilter.PUNCH_PARTY_DRINK.key
            }
            CategoryDrinkFilter.BEER -> filteredList?.filter {
                it.strCategory == CategoryDrinkFilter.BEER.key
            }
            CategoryDrinkFilter.SOFT_DRINK_SODA -> filteredList?.filter {
                it.strCategory == CategoryDrinkFilter.SOFT_DRINK_SODA.key
            }
            CategoryDrinkFilter.NONE -> filteredList
        }
    }

    fun setAlcoholFilters(menuItem: MenuItem) {
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
            else -> AlcoholDrinkFilter.NONE
        }
    }

    fun setCategoryFilter(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.item_category_ordinary -> {
                categoryDrinkFilterLiveData.value = CategoryDrinkFilter.ORDINARY_DRINK
            }
            R.id.item_category_cocktail -> {
                categoryDrinkFilterLiveData.value = CategoryDrinkFilter.COCKTAIL
            }
            R.id.item_category_milk -> {
                categoryDrinkFilterLiveData.value = CategoryDrinkFilter.MILK_FLOAT_SHAKE
            }
            R.id.item_category_other -> {
                categoryDrinkFilterLiveData.value = CategoryDrinkFilter.OTHER_UNKNOWN
            }
            R.id.item_category_cocoa -> {
                categoryDrinkFilterLiveData.value = CategoryDrinkFilter.COCOA
            }
            R.id.item_category_shot -> {
                categoryDrinkFilterLiveData.value = CategoryDrinkFilter.SHOT
            }
            R.id.item_category_coffee -> {
                categoryDrinkFilterLiveData.value = CategoryDrinkFilter.COFFEE_TEA
            }
            R.id.item_category_homemadeLiqueur -> {
                categoryDrinkFilterLiveData.value = CategoryDrinkFilter.HOMEMADE_LIQUEUR
            }
            R.id.item_category_punch -> {
                categoryDrinkFilterLiveData.value = CategoryDrinkFilter.PUNCH_PARTY_DRINK
            }
            R.id.item_category_beer -> {
                categoryDrinkFilterLiveData.value = CategoryDrinkFilter.BEER
            }
            R.id.item_category_softDrink -> {
                categoryDrinkFilterLiveData.value = CategoryDrinkFilter.SOFT_DRINK_SODA
            }
            else -> CategoryDrinkFilter.NONE
        }
    }

    fun setSortValue(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.menu_sort_recent -> {
                sortDrinkLiveData.value = SortDrink.RECENT
            }
            R.id.menu_sort_nameAscending -> {
                sortDrinkLiveData.value = SortDrink.NAME_ASCENDING
            }
            R.id.menu_sort_nameDescending -> {
                sortDrinkLiveData.value = SortDrink.NAME_DESCENDING
            }
            R.id.menu_sort_alcoholFirst -> {
                sortDrinkLiveData.value = SortDrink.ALCOHOL_FIRST
            }
            R.id.menu_sort_nonAlcoholFirst -> {
                sortDrinkLiveData.value = SortDrink.NON_ALCOHOL_FIRST
            }
            R.id.menu_sort_ingredientAscending -> {
                sortDrinkLiveData.value = SortDrink.INGREDIENT_ASCENDING
            }
            R.id.menu_sort_ingredientDescending -> {
                sortDrinkLiveData.value = SortDrink.INGREDIENT_DESCENDING
            }
        }
    }

    fun setStartParam() {
        alcoholDrinkFilterLiveData.value = AlcoholDrinkFilter.NONE
        categoryDrinkFilterLiveData.value = CategoryDrinkFilter.NONE
        sortDrinkLiveData.value = SortDrink.RECENT
    }

    fun refreshParam() {
        alcoholDrinkFilterLiveData.value = alcoholDrinkFilterLiveData.value
        categoryDrinkFilterLiveData.value = categoryDrinkFilterLiveData.value
        sortDrinkLiveData.value = sortDrinkLiveData.value
    }

    fun dropFilters() {
        alcoholDrinkFilterLiveData.value = AlcoholDrinkFilter.NONE
        categoryDrinkFilterLiveData.value = CategoryDrinkFilter.NONE
    }
}