package com.slutsenko.cocktailapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder> {
    private Context context;
    private ArrayList<Cocktail> cocktailsList;

    public CocktailAdapter(Context context, ArrayList<Cocktail> cocktail) {
        this.context = context;
        this.cocktailsList = cocktail;
    }

    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cocktail, parent, false);
        return new CocktailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailViewHolder holder, int position) {
        Cocktail currentCocktail = cocktailsList.get(position);

        String imageURL = currentCocktail.getStrDrinkThumb();
        String cocktailName = currentCocktail.getStrDrink();

        holder.tv_cocktail_name.setText(cocktailName);
        Glide.with(context)
                .load(imageURL)
                .centerCrop()
                .into(holder.iv_cocktail);

    }

    @Override
    public int getItemCount() {
        return cocktailsList.size();
    }

    class CocktailViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_cocktail;
        TextView tv_cocktail_name;

        CocktailViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cocktail = itemView.findViewById(R.id.iv_cocktail);
            tv_cocktail_name = itemView.findViewById(R.id.tv_cocktail_name);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, AboutCocktailActivity.class);
                Cocktail cocktail = cocktailsList.get(getAdapterPosition());
                intent.putExtra("cocktail", cocktail);
                context.startActivity(intent);
            });
        }
    }
}
