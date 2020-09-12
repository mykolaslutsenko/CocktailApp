package com.slutsenko.cocktailapp.presentation.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailAlcoholType
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailCategory
import com.slutsenko.cocktailapp.presentation.model.cocktail.DrinkFilter
import com.slutsenko.cocktailapp.feature.main.MainFragmentViewModel

class FilterAdapter(val filter: List<DrinkFilter>, val context: Context, val viewModel: MainFragmentViewModel)
    : RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        return FilterViewHolder(LayoutInflater.from(context).inflate(R.layout.widget_filter, parent, false))
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val item = filter[position]
        if (item is CocktailAlcoholType) {
            if (item != CocktailAlcoholType.UNDEFINED) {
                holder.filterValue.text = filter[position].key
                holder.icon.setImageResource(R.drawable.ic_alcohol_24)
                holder.icon.visibility = View.VISIBLE
                holder.close.visibility = View.VISIBLE
                holder.container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAlcoholFilter))
            }

        } else if (item is CocktailCategory) {
            if (item != CocktailCategory.UNDEFINED) {
                holder.filterValue.text = filter[position].key
                holder.icon.setImageResource(R.drawable.ic_category_24)
                holder.icon.visibility = View.VISIBLE
                holder.close.visibility = View.VISIBLE
                holder.container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorCategoryFilter))
            }
        }
    }

    override fun getItemCount(): Int {
        return filter.size
    }

    inner class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var filterValue = itemView.findViewById<TextView>(R.id.txt_rv_filter_value)
        var icon = itemView.findViewById<ImageView>(R.id.iv_rv_filter_image)
        var close = itemView.findViewById<ImageView>(R.id.btn_rv_filter_close)
        var container = itemView.findViewById<CardView>(R.id.container_rv_filter)

        init {
            close.setOnClickListener {
                if (filter[adapterPosition] is CocktailAlcoholType) {
                    viewModel.alcoholDrinkFilterLiveData.value = CocktailAlcoholType.UNDEFINED
                } else if (filter[adapterPosition] is CocktailCategory) {
                    viewModel.categoryDrinkFilterLiveData.value = CocktailCategory.UNDEFINED
                }
            }
        }
    }


}