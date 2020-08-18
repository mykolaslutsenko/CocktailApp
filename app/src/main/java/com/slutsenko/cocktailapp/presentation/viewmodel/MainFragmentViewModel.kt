package com.slutsenko.cocktailapp.presentation.viewmodel

import android.view.MenuItem
import androidx.lifecycle.*
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.databinding.adapter.Page
import com.slutsenko.cocktailapp.extension.log
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

    val EXTRA_KEY_ALCOHOL = "EXTRA_KEY_ALCOHOL"
    val EXTRA_KEY_CATEGORY = "EXTRA_KEY_CATEGORY"
    val EXTRA_KEY_SORT = "EXTRA_KEY_SORT"
    val EXTRA_KEY_SORT_ID = "EXTRA_KEY_SORT_ID"

    var cocktailDBLiveData: LiveData<List<CocktailModel>> = cocktailRepository.cocktailListLiveData.map(mapper::mapTo)

    lateinit var filteredList: List<CocktailModel>

    val viewPagerLiveData: MutableLiveData<Page> = MutableLiveData()

    var alcoholDrinkFilterLiveData: MutableLiveData<CocktailAlcoholType> = object : MutableLiveData<CocktailAlcoholType>() {

        override fun setValue(value: CocktailAlcoholType?) {
            savedStateHandle.set(EXTRA_KEY_ALCOHOL, value?.ordinal)
            super.setValue(value)
        }
        override fun getValue(): CocktailAlcoholType? {
            return CocktailAlcoholType.values()[savedStateHandle.get<Int>(EXTRA_KEY_ALCOHOL) ?: 0]
        }
    }

    var categoryDrinkFilterLiveData: MutableLiveData<CocktailCategory> = object : MutableLiveData<CocktailCategory>() {
        override fun setValue(value: CocktailCategory?) {
            savedStateHandle.set(EXTRA_KEY_CATEGORY, value?.ordinal)
            super.setValue(value)
        }
        override fun getValue(): CocktailCategory? {
            return CocktailCategory.values()[savedStateHandle.get<Int>(EXTRA_KEY_CATEGORY) ?: 0]
        }
    }

    var sortDrinkLiveData: MutableLiveData<SortDrink> = object :MutableLiveData<SortDrink>() {
        override fun setValue(value: SortDrink?) {
            savedStateHandle.set(EXTRA_KEY_SORT, value?.ordinal)
            super.setValue(value)
        }
        override fun getValue(): SortDrink? {
            return SortDrink.values()[savedStateHandle.get<Int>(EXTRA_KEY_SORT) ?: 0]
        }
    }

    var sortIdLiveData:MutableLiveData<Int> = object : MutableLiveData<Int> (){

        override fun setValue(value: Int?) {
            savedStateHandle.set(EXTRA_KEY_SORT_ID, value)
            super.setValue(value)
        }

        override fun getValue(): Int? {
            return savedStateHandle.get(EXTRA_KEY_SORT_ID) ?:R.id.menu_sort_recent
        }
    }

    var favoriteLiveData: MutableLiveData<List<CocktailModel>> = MutableLiveData()

    var cocktailQuantityLiveData: MutableLiveData<Int> = MutableLiveData()

    var historyLiveData =
            MediatorLiveData<List<CocktailModel>>().apply {
                fun filterAndSort() {
                    value = filterAndSortCocktailList()
                }
                addSource(cocktailDBLiveData) {
                    filterAndSort()
                }
                addSource(alcoholDrinkFilterLiveData) {
                    filterAndSort()
                }
                addSource(categoryDrinkFilterLiveData) {
                    filterAndSort()
                }
                addSource(sortDrinkLiveData) {
                    filterAndSort()
                }


            }
//


    private fun filterAndSortCocktailList(): List<CocktailModel> {
        filteredList = cocktailDBLiveData.value ?: emptyList()
        filteredList.size.log
        alcoholDrinkFilterLiveData.value.log
        categoryDrinkFilterLiveData.value.log
        sortDrinkLiveData.value.log
        filterAlcohol(alcoholDrinkFilterLiveData.value ?: CocktailAlcoholType.UNDEFINED)
        filterCategory(categoryDrinkFilterLiveData.value ?: CocktailCategory.UNDEFINED)
        sortCocktailList(sortDrinkLiveData.value ?: SortDrink.RECENT)
        filteredList.size.log
        return filteredList ?: emptyList()
    }

    private fun sortCocktailList(sort: SortDrink) {
        filteredList = when (sort) {
            SortDrink.RECENT -> {
                filteredList?.sortedBy { it.id }
            }
            SortDrink.NAME_ASCENDING -> {
                filteredList?.sortedBy { it.names.def }
            }
            SortDrink.NAME_DESCENDING -> {
                filteredList?.sortedByDescending { it.names.def }
            }
            SortDrink.ALCOHOL_FIRST -> {
                filteredList?.sortedBy { it.id }
            }
            SortDrink.NON_ALCOHOL_FIRST -> {
                filteredList?.sortedBy { it.id }
            }
            SortDrink.INGREDIENT_ASCENDING -> {
                filteredList?.sortedBy { it.ingredients.size }
            }
            SortDrink.INGREDIENT_DESCENDING -> {
                filteredList?.sortedByDescending { it.ingredients.size }
            }
            else -> filteredList
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
            else -> alcoholDrinkFilterLiveData.value = CocktailAlcoholType.UNDEFINED
        }
    }

    private fun filterAlcohol(alcoholDrinkFilter: CocktailAlcoholType) {
        if (alcoholDrinkFilter != CocktailAlcoholType.UNDEFINED) {
            filteredList = filteredList.filter { it.alcoholType == alcoholDrinkFilter }
        }
    }

    private fun filterCategory(categoryDrinkFilter: CocktailCategory) {
        if (categoryDrinkFilter != CocktailCategory.UNDEFINED) {
            filteredList = filteredList.filter { it.category == categoryDrinkFilter }
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
            else -> categoryDrinkFilterLiveData.value = CocktailCategory.UNDEFINED
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

    fun dropFilters() {
        alcoholDrinkFilterLiveData.value = CocktailAlcoholType.UNDEFINED
        categoryDrinkFilterLiveData.value = CocktailCategory.UNDEFINED
    }

    fun saveToDb(cocktail: CocktailModel) {
        launchRequest {
            cocktailRepository.addOrReplaceCocktail(mapper.mapFrom(cocktail))
        }
    }

    fun deleteCocktail(cocktail: CocktailModel) {
        launchRequest {
            cocktailRepository.deleteCocktails(mapper.mapFrom(cocktail))
        }
    }
}