package com.slutsenko.cocktailapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.slutsenko.cocktailapp.Base;
import com.slutsenko.cocktailapp.Cocktail;
import com.slutsenko.cocktailapp.R;
import com.slutsenko.cocktailapp.service.DrinkService;

import java.util.Objects;

import static com.slutsenko.cocktailapp.ui.MainActivity.cocktailDatabase;

public class AboutCocktailActivity extends Base {

    Cocktail cocktail;
    ImageView image;
    TextView infoItem;
    TextView infoValue;
    TextView ingredientTitle;
    TextView ingredientNames;
    TextView ingredientValues;
    TextView instructionsTitle;
    TextView instructionsValue;

    @Override
    protected int myView() {
        return R.layout.activity_about_cocktail;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startService(new Intent(this, DrinkService.class));
        Snackbar.make(findViewById(android.R.id.content), "Random", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void activityCreated() {
        cocktail = (Cocktail) getIntent().getSerializableExtra("cocktail");
        Objects.requireNonNull(getSupportActionBar()).setTitle(cocktail.getStrDrink());

        findComponents();
        customizeComponents();

        cocktailDatabase.cocktailDao().addCocktail(cocktail);

    }

    private void findComponents() {
        image = findViewById(R.id.iv_cocktail_full);
        infoItem = findViewById(R.id.tv_info_item);
        infoValue = findViewById(R.id.tv_info_value);
        ingredientTitle = findViewById(R.id.tv_ingredient_title);
        ingredientNames = findViewById(R.id.tv_ingredient_names);
        ingredientValues = findViewById(R.id.tv_ingredient_values);
        instructionsTitle = findViewById(R.id.tv_instructions_title);
        instructionsValue = findViewById(R.id.tv_instructions_value);
    }


    private void customizeComponents() {
        String strInfoItem = "Name: " + "\n" + "Alcoholic: " + "\n" + "Glass: ";
        String strInfoValue = cocktail.getStrDrink() + "\n" +
                cocktail.getStrAlcoholic() + "\n" + cocktail.getStrGlass();
        String strIngredientNames = cocktail.getIngredients();
        String strIngredientValues = cocktail.getMeasures();
        String strIngredientTitle = "Ingredients";
        String strInstructionsTitle = "Instructions";

        Glide.with(this).load(cocktail.getStrDrinkThumb()).into(image);
        infoItem.setText(strInfoItem);
        infoValue.setText(strInfoValue);
        ingredientTitle.setText(strIngredientTitle);
        ingredientNames.setText(strIngredientNames);
        ingredientValues.setText(strIngredientValues);
        instructionsTitle.setText(strInstructionsTitle);
        instructionsValue.setText(cocktail.getStrInstructions());
    }
}
