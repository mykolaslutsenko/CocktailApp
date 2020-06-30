package com.slutsenko.cocktailapp.ui

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.slutsenko.cocktailapp.BaseActivity
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.databinding.ActivitySearchBinding
import com.slutsenko.cocktailapp.ui.presentation.adapter.list.CocktailAdapter
import com.slutsenko.cocktailapp.network.CocktailList
import com.slutsenko.cocktailapp.network.JsonPlaceholderApi
import com.slutsenko.cocktailapp.ui.fragment.MainFragment.Companion.COLUMN
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    var cocktailAdapter: CocktailAdapter? = null

    override fun myView(): Int {
        return R.layout.activity_search
    }

    override fun activityCreated() {
        tv_answer.setText( R.string.enter_text)
        rv_search.layoutManager = GridLayoutManager(this, COLUMN)
        tiet_text.setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search()
                true
            } else {
                false
            }
        }
        til_search.setStartIconOnClickListener { v: View? -> search() }
    }

    private fun search() {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi::class.java)
        val enteredText = tiet_text.text.toString()
        if (enteredText == "") {
            tv_answer.setText(R.string.enter_text)
            rv_search.adapter = null
        } else {
            val call = jsonPlaceholderApi
                    .getPosts(enteredText)
            call?.enqueue(object : Callback<CocktailList?> {
                override fun onResponse(call: Call<CocktailList?>,
                                        response: Response<CocktailList?>) {
                    val cocktail = response.body()!!.cocktails
                    if (cocktail != null) {
                        cocktailAdapter = CocktailAdapter(this@SearchActivity, cocktail)
                        rv_search.adapter = cocktailAdapter
                        tv_answer.text = ""
                    } else {
                        tv_answer.setText(R.string.no_found)
                        rv_search.adapter = null
                    }
                }

                override fun onFailure(call: Call<CocktailList?>, t: Throwable) {
                    tv_answer.text = t.message
                }
            })
        }
    }

    companion object {
        private const val URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    }
}