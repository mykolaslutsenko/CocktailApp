package com.slutsenko.cocktailapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private static final int COLUMN = 2;
    public static CocktailDatabase cocktailDatabase;
    CocktailAdapter cocktailAdapter;
    RecyclerView recyclerView;
    ArrayList<Cocktail> cocktail;
    FloatingActionButton floatingActionButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.fab_search);
        textView = findViewById(R.id.tv_history);

        MainActivity.this.setTitle("          " + "Cocktail App");


        cocktailDatabase = Room.databaseBuilder(getApplicationContext(),
                CocktailDatabase.class, "cocktail_data2").allowMainThreadQueries().build();
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
