package com.slutsenko.cocktailapp.ui;

import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.slutsenko.cocktailapp.Base;
import com.slutsenko.cocktailapp.Cocktail;
import com.slutsenko.cocktailapp.R;
import com.slutsenko.cocktailapp.adapter.list.CocktailAdapter;
import com.slutsenko.cocktailapp.network.CocktailList;
import com.slutsenko.cocktailapp.network.JsonPlaceholderApi;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchActivity extends Base {

    private static final String URL = "https://www.thecocktaildb.com/api/json/v1/1/";
    private static final int COLUMN = 2;
    RecyclerView recyclerView;
    CocktailAdapter cocktailAdapter;
    ArrayList<Cocktail> cocktail;
    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;
    TextView textView;

    @Override
    protected int myView() {
        return R.layout.activity_search;
    }

    @Override
    protected void activityCreated() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        textInputLayout = findViewById(R.id.til_search);
        textInputEditText = findViewById(R.id.tiet_text);
        textView = findViewById(R.id.tv_answer);
        textView.setText(R.string.enter_text);

        recyclerView = findViewById(R.id.rv_search);
        recyclerView.setLayoutManager(new GridLayoutManager(this, COLUMN));

        textInputEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search();
                return true;
            }
            return false;
        });

        textInputLayout.setStartIconOnClickListener(v -> search());

    }

    private void search() {
        String enteredText;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);
        enteredText = Objects.requireNonNull(textInputLayout.getEditText()).getText().toString();
        if (enteredText.equals("")) {
            textView.setText(R.string.enter_text);
            recyclerView.setAdapter(null);
        } else {
            Call<CocktailList> call = jsonPlaceholderApi
                    .getPosts(enteredText);
            call.enqueue(new Callback<CocktailList>() {
                @Override
                public void onResponse(@NonNull Call<CocktailList> call,
                                       @NonNull Response<CocktailList> response) {
                    cocktail = response.body() != null ? response.body().getCocktails() : null;
                    if (cocktail != null) {
                        cocktailAdapter = new CocktailAdapter(SearchActivity.this, cocktail);
                        recyclerView.setAdapter(cocktailAdapter);
                        textView.setText("");
                    } else {
                        textView.setText(R.string.no_found);
                        recyclerView.setAdapter(null);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<CocktailList> call, @NonNull Throwable t) {
                    textView.setText(t.getMessage());
                }
            });
        }
    }
}