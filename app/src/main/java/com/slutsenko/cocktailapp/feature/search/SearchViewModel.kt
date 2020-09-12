package com.slutsenko.cocktailapp.feature.search

import android.app.Application
import androidx.lifecycle.*
import com.slutsenko.cocktailapp.data.repository.source.CocktailRepository
import com.slutsenko.cocktailapp.presentation.extension.debounce
import com.slutsenko.cocktailapp.presentation.mapper.CocktailModelMapper
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailModel
import com.slutsenko.cocktailapp.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.Job

class SearchViewModel(application: Application,
                      private val cocktailRepository: CocktailRepository,
                      private val mapper: CocktailModelMapper,
                      savedStateHandle: SavedStateHandle

): BaseViewModel(application) {

    var searchLiveData: MutableLiveData<String>? = MutableLiveData()
    var answerLiveData: MutableLiveData<String>? = MutableLiveData()

    private var searchJob: Job? = null

    val searchResultCocktailListLiveData: LiveData<List<CocktailModel>> = MutableLiveData(emptyList())

    val searchQueryLiveData = MutableLiveData<String>(null)

    private val searchQueryDebounceLiveData = searchQueryLiveData.map {it}.debounce(1000L)

    private val searchTriggerObserver = Observer<String?> {
        searchCocktail(it)
    }

    init {
        searchQueryDebounceLiveData.observeForever(searchTriggerObserver)
    }

    override fun onCleared() {
        super.onCleared()
        searchQueryDebounceLiveData.removeObserver(searchTriggerObserver)
    }

    private fun searchCocktail(query: String?) {
        if (searchJob?.isActive == true) searchJob?.cancel()
        searchJob = launchRequest(searchResultCocktailListLiveData) {
            when {
                query.isNullOrEmpty() -> emptyList()
                else -> {
                    cocktailRepository.searchCocktailRemote(query).map(mapper::mapTo)
                }
            }
        }
    }
}