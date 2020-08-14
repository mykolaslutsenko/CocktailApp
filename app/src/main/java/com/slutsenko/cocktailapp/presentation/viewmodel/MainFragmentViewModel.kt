package com.slutsenko.cocktailapp.presentation.viewmodel

import android.view.MenuItem
import androidx.lifecycle.*
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.databinding.adapter.Page
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailAlcoholType
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailCategory
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailModel
import com.slutsenko.cocktailapp.presentation.model.cocktail.SortDrink
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel

class MainFragmentViewModel(val cocktailRepository: CocktailRepository,
                            val mapper: CocktailModelMapper,
                            savedStateHandle: SavedStateHandle
) : BaseViewModel() {


    var cocktailDBLiveData:LiveData<List<CocktailModel>> = cocktailRepository.cocktailListLiveData.map(mapper::mapTo)

//
//    var cocktailDBLiveData3:LiveData<List<CocktailModel>> = cocktailRepository.cocktailListLiveData.map { mapper.mapTo(it) }
//
//
//
//
//    //cocktailRepository.cocktailListLiveData.map { mapper.mapTo(it) }
//    fun getBD() {
//            cocktailDBLiveData2 = cocktailRepository.getCocktailLiveData().map { mapper.mapTo(it) }
//    }
//
//    fun getFirstCocktail() {
//        launchRequest {
//            firstCocktail = mapper.mapTo(cocktailRepository.getCocktailById(11021L)!!)
//        }
//    }

//    private val triggerObserver: Observer<in Any?> = Observer { }
//    val cocktailLiveData = cocktailRepository.cocktailListLiveData.map { mapper.mapTo(it) }
//    var cocktailDBLiveData2:List<CocktailModel> = cocktailRepository.getCocktailLiveData().map { mapper.mapTo(it) }
//
//    init {
//        //cocktailDBLiveData2.observeForever(triggerObserver)
//    }
//
//    override fun onCleared() {
//        //cocktailDBLiveData2.removeObserver(triggerObserver)
//        super.onCleared()
//    }


    var filteredList: List<CocktailModel>? = null

    val viewPagerLiveData: MutableLiveData<Page> = MutableLiveData()


    val alcoholDrinkFilterLiveData: MutableLiveData<CocktailAlcoholType> = MutableLiveData()

    var categoryDrinkFilterLiveData: MutableLiveData<CocktailCategory> = MutableLiveData()

    var historyLiveData: MutableLiveData<List<CocktailModel>> = MutableLiveData()

    var favoriteLiveData: MutableLiveData<List<CocktailModel>> = MutableLiveData()

    val sortDrinkLiveData: MutableLiveData<SortDrink> = MutableLiveData()

    val showNavigationBarTitlesLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var cocktailQuantityLiveData: MutableLiveData<Int> = MutableLiveData()

    var mediatorLiveData: MutableLiveData<List<CocktailModel>> = MediatorLiveData<List<CocktailModel>>().apply {
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
//


    private fun filterAndSortCocktailList(): List<CocktailModel> {
        filteredList = cocktailDBLiveData.value!!
        filterAlcohol(alcoholDrinkFilterLiveData.value ?: CocktailAlcoholType.UNDEFINED)
        filterCategory(categoryDrinkFilterLiveData.value ?: CocktailCategory.UNDEFINED)
        sortCocktailList(sortDrinkLiveData.value ?: SortDrink.RECENT)
        return filteredList!!
    }

    private fun sortCocktailList(sort: SortDrink) {
        filteredList = when (sort) {
            SortDrink.RECENT -> {
                filteredList?.sortedBy { it.id }
            }
            SortDrink.NAME_ASCENDING -> {
                filteredList?.sortedBy { it.id }
            }
            SortDrink.NAME_DESCENDING -> {
                filteredList?.sortedByDescending { it.id }
            }
            SortDrink.ALCOHOL_FIRST -> {
                filteredList?.sortedBy { it.id }
            }
            SortDrink.NON_ALCOHOL_FIRST -> {
                filteredList?.sortedBy { it.id }
            }
            SortDrink.INGREDIENT_ASCENDING -> {
                filteredList?.sortedBy { it.id }
            }
            SortDrink.INGREDIENT_DESCENDING -> {
                filteredList?.sortedByDescending { it.id }
            }
        }
    }

    private fun filterAlcohol(alcoholDrinkFilter: CocktailAlcoholType) {
        filteredList = when (alcoholDrinkFilter) {
            CocktailAlcoholType.ALCOHOLIC -> filteredList?.filter {
                it.alcoholType == CocktailAlcoholType.ALCOHOLIC
            }
            CocktailAlcoholType.NON_ALCOHOLIC -> filteredList?.filter {
                it.alcoholType == CocktailAlcoholType.NON_ALCOHOLIC
            }
            CocktailAlcoholType.OPTIONAL_ALCOHOL -> filteredList?.filter {
                it.alcoholType == CocktailAlcoholType.OPTIONAL_ALCOHOL
            }
            CocktailAlcoholType.UNDEFINED -> filteredList
        }
    }

    private fun filterCategory(categoryDrinkFilter: CocktailCategory) {
        filteredList = when (categoryDrinkFilter) {
            CocktailCategory.ORDINARY_DRINK -> filteredList?.filter {
                it.category == CocktailCategory.ORDINARY_DRINK
            }
            CocktailCategory.COCKTAIL -> filteredList?.filter {
                it.category == CocktailCategory.COCKTAIL
            }
            CocktailCategory.MILK_FLOAT_SHAKE -> filteredList?.filter {
                it.category == CocktailCategory.MILK_FLOAT_SHAKE
            }
            CocktailCategory.OTHER_UNKNOWN -> filteredList?.filter {
                it.category == CocktailCategory.OTHER_UNKNOWN
            }
            CocktailCategory.COCOA -> filteredList?.filter {
                it.category == CocktailCategory.COCOA
            }
            CocktailCategory.SHOT -> filteredList?.filter {
                it.category == CocktailCategory.SHOT
            }
            CocktailCategory.COFFEE_TEA -> filteredList?.filter {
                it.category == CocktailCategory.COFFEE_TEA
            }
            CocktailCategory.HOMEMADE_LIQUEUR -> filteredList?.filter {
                it.category == CocktailCategory.HOMEMADE_LIQUEUR
            }
            CocktailCategory.PUNCH_PARTY_DRINK -> filteredList?.filter {
                it.category == CocktailCategory.PUNCH_PARTY_DRINK
            }
            CocktailCategory.BEER -> filteredList?.filter {
                it.category == CocktailCategory.BEER
            }
            CocktailCategory.SOFT_DRINK_SODA -> filteredList?.filter {
                it.category == CocktailCategory.SOFT_DRINK_SODA
            }
            CocktailCategory.UNDEFINED -> filteredList
        }
    }

    fun setAlcoholFilters(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.item_alcoholic -> {
                alcoholDrinkFilterLiveData.value = CocktailAlcoholType.ALCOHOLIC
            }
            R.id.item_nonAlcoholic -> {
                alcoholDrinkFilterLiveData.value = CocktailAlcoholType.NON_ALCOHOLIC
            }
            R.id.item_optionalAlcohol -> {
                alcoholDrinkFilterLiveData.value = CocktailAlcoholType.OPTIONAL_ALCOHOL
            }
            else -> CocktailAlcoholType.UNDEFINED
        }
    }

    fun setCategoryFilter(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.item_category_ordinary -> {
                categoryDrinkFilterLiveData.value = CocktailCategory.ORDINARY_DRINK
            }
            R.id.item_category_cocktail -> {
                categoryDrinkFilterLiveData.value = CocktailCategory.COCKTAIL
            }
            R.id.item_category_milk -> {
                categoryDrinkFilterLiveData.value = CocktailCategory.MILK_FLOAT_SHAKE
            }
            R.id.item_category_other -> {
                categoryDrinkFilterLiveData.value = CocktailCategory.OTHER_UNKNOWN
            }
            R.id.item_category_cocoa -> {
                categoryDrinkFilterLiveData.value = CocktailCategory.COCOA
            }
            R.id.item_category_shot -> {
                categoryDrinkFilterLiveData.value = CocktailCategory.SHOT
            }
            R.id.item_category_coffee -> {
                categoryDrinkFilterLiveData.value = CocktailCategory.COFFEE_TEA
            }
            R.id.item_category_homemadeLiqueur -> {
                categoryDrinkFilterLiveData.value = CocktailCategory.HOMEMADE_LIQUEUR
            }
            R.id.item_category_punch -> {
                categoryDrinkFilterLiveData.value = CocktailCategory.PUNCH_PARTY_DRINK
            }
            R.id.item_category_beer -> {
                categoryDrinkFilterLiveData.value = CocktailCategory.BEER
            }
            R.id.item_category_softDrink -> {
                categoryDrinkFilterLiveData.value = CocktailCategory.SOFT_DRINK_SODA
            }
            else -> CocktailCategory.UNDEFINED
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
        alcoholDrinkFilterLiveData.value = CocktailAlcoholType.UNDEFINED
        categoryDrinkFilterLiveData.value = CocktailCategory.UNDEFINED
        sortDrinkLiveData.value = SortDrink.RECENT
    }

    fun refreshParam() {
        alcoholDrinkFilterLiveData.value = alcoholDrinkFilterLiveData.value
        categoryDrinkFilterLiveData.value = categoryDrinkFilterLiveData.value
        sortDrinkLiveData.value = sortDrinkLiveData.value
    }

    fun dropFilters() {
        alcoholDrinkFilterLiveData.value = CocktailAlcoholType.UNDEFINED
        categoryDrinkFilterLiveData.value = CocktailCategory.UNDEFINED
    }

}