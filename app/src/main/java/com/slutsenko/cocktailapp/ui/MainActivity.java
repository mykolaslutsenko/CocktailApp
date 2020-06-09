package com.slutsenko.cocktailapp.ui;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.slutsenko.cocktailapp.Base;
import com.slutsenko.cocktailapp.Cocktail;
import com.slutsenko.cocktailapp.R;
import com.slutsenko.cocktailapp.adapter.list.CocktailAdapter;
import com.slutsenko.cocktailapp.db.CocktailDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Base {
    private static final int COLUMN = 2;
    public static CocktailDatabase cocktailDatabase;
    CocktailAdapter cocktailAdapter;
    RecyclerView recyclerView;
    ArrayList<Cocktail> cocktail;
    FloatingActionButton floatingActionButton;
    TextView textView;

    @Override
    protected int myView() {
        return R.layout.activity_main;
    }

    @Override
    protected void activityCreated() {
        floatingActionButton = findViewById(R.id.fab_search);
        textView = findViewById(R.id.tv_history);

        MainActivity.this.setTitle("          " + "Cocktail App");


        cocktailDatabase = Room.databaseBuilder(getApplicationContext(),
                CocktailDatabase.class, "cocktail_data").allowMainThreadQueries().build();
        cocktail = (ArrayList<Cocktail>) cocktailDatabase.cocktailDao().getCocktails();

        if (cocktail.isEmpty()) {
            textView.setText(R.string.history);
        } else {
            Collections.reverse(cocktail);
            cocktailAdapter = new CocktailAdapter(MainActivity.this, cocktail);

            recyclerView = findViewById(R.id.rv_database);
            recyclerView.setLayoutManager(new GridLayoutManager(this, COLUMN));
            recyclerView.setAdapter(cocktailAdapter);
            textView.setText("");
        }

        floatingActionButton.setOnClickListener(
                v -> startActivity(new Intent(MainActivity.this, SearchActivity.class)));
    }
}
