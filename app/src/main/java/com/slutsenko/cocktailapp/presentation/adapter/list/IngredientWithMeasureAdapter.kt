package com.slutsenko.cocktailapp.presentation.adapter.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.slutsenko.cocktailapp.R

class IngredientWithMeasureAdapter(var context: Context, var ingredients: List<String>?, var measures: List<String>?) : RecyclerView.Adapter<IngredientWithMeasureAdapter.IngredientAndMeasureViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientAndMeasureViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_ingredient_and_measure, parent, false)
        return IngredientAndMeasureViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientAndMeasureViewHolder, position: Int) {
        holder.number.text = (position + 1).toString()
        holder.ingredient.text = ingredients?.get(position)
        if (position < measures?.size!!) {
            holder.measure.text = measures?.get(position)
        }
    }

    override fun getItemCount(): Int {
        return ingredients?.size!!
    }

    inner class IngredientAndMeasureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var number = itemView.findViewById<TextView>(R.id.tv_number)
        var ingredient = itemView.findViewById<TextView>(R.id.tv_widget_ingredient)
        var measure = itemView.findViewById<TextView>(R.id.tv_widget_measure)

    }

}