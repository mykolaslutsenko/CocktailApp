package com.slutsenko.cocktailapp.presentation.ui.activity

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.data.network.CocktailList
import com.slutsenko.cocktailapp.data.network.CocktailNetModel
import com.slutsenko.cocktailapp.data.network.JsonPlaceholderApi
import com.slutsenko.cocktailapp.databinding.ActivitySearchBinding
import com.slutsenko.cocktailapp.presentation.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.presentation.model.cocktail.*
import com.slutsenko.cocktailapp.presentation.ui.base.BaseActivity
import com.slutsenko.cocktailapp.presentation.ui.fragment.MainFragment.Companion.COLUMN
import com.slutsenko.cocktailapp.presentation.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass


class SearchActivity : BaseActivity<SearchViewModel, ActivitySearchBinding>() {

    override val viewModelClass: KClass<SearchViewModel>
        get() = SearchViewModel::class

    // override val viewModel: SearchViewModel by viewModels()
    var cocktailAdapter: CocktailAdapter? = null
    private lateinit var jsonPlaceholderApi: JsonPlaceholderApi

    override fun myView(): Int {
        return R.layout.activity_search
    }

    override fun activityCreated() {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi::class.java)


        viewModel.answerLiveData?.value = getString(R.string.enter_text)
        rv_search.layoutManager = GridLayoutManager(this, COLUMN)

        viewModel.searchLiveData?.observe(this, Observer {
            search()
        })
    }

    override fun configureDataBinding(binding: ActivitySearchBinding) {
        super.configureDataBinding(binding)
        binding.viewModel = viewModel
    }


    private fun search() {


        if (viewModel.searchLiveData?.value.isNullOrEmpty()) {
            viewModel.answerLiveData?.value = getString(R.string.enter_text)
            rv_search.adapter = null
        } else {
            val call = jsonPlaceholderApi.getPosts(viewModel.searchLiveData?.value)
            call?.enqueue(object : Callback<CocktailList?> {
                override fun onResponse(call: Call<CocktailList?>, response: Response<CocktailList?>) {
                    val cocktail = response.body()?.cocktails
                    if (cocktail != null) {
                        cocktailAdapter = CocktailAdapter(this@SearchActivity, cocktail.map { mapNetToLocal(it) })
                        rv_search.adapter = cocktailAdapter
                        viewModel.answerLiveData?.value = ""
                    } else {
                        viewModel.answerLiveData?.value = getString(R.string.no_found)
                        rv_search.adapter = null
                    }
                }

                override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                    viewModel.answerLiveData?.value = t.message
                }
            })
        }
    }

    companion object {
        private const val URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    }

    // тимчасово, в наступних дз перероблю через репозиторій


    fun mapNetToLocal(db: CocktailNetModel): CocktailModel = with(db) {
        CocktailModel(
                id = id!!,
                 names = LocalizedStringModel(
                                names,
                                strDrinkAlternate,
                                strDrinkES,
                                strDrinkDE,
                                strDrinkFR,
                                strDrinkZHHANS,
                                strDrinkZHHANT
                            ),
                category = CocktailCategory.values().firstOrNull { it.key == category }
                        ?: CocktailCategory.UNDEFINED,
                alcoholType = CocktailAlcoholType.values().firstOrNull() { it.key == alcoholType }
                        ?: CocktailAlcoholType.UNDEFINED,
                glass = CocktailGlass.values().firstOrNull { it.key == glass }
                        ?: CocktailGlass.UNDEFINED,
                image = image!!,
                instructions = LocalizedStringModel(
                        instruction,
                        null,
                        strInstructionsES,
                        strInstructionsDE,
                        strInstructionsFR,
                        strInstructionsZHHANS,
                        strInstructionsZHHANT
                ),
                //instructions = instructions.run(localizedStringRepoModelMapper::mapDbToRepo),
                ingredients = listOfNotNull(strIngredient1, strIngredient2, strIngredient3,
                        strIngredient4, strIngredient5, strIngredient6,
                        strIngredient7, strIngredient8, strIngredient9,
                        strIngredient10, strIngredient11, strIngredient12,
                        strIngredient13, strIngredient14, strIngredient15),
                measures = listOfNotNull(strMeasure1, strMeasure2, strMeasure3,
                        strMeasure4, strMeasure5, strMeasure6,
                        strMeasure7, strMeasure8, strMeasure9,
                        strMeasure10, strMeasure11, strMeasure12,
                        strMeasure13, strMeasure14, strMeasure15)
        )
    }
}