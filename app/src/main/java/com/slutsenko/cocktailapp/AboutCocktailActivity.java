package com.slutsenko.cocktailapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import static com.slutsenko.cocktailapp.MainActivity.cocktailDatabase;

public class AboutCocktailActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_cocktail);

        cocktail = (Cocktail) getIntent().getSerializableExtra("cocktail");
        getSupportActionBar().setTitle(cocktail.getStrDrink());

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
