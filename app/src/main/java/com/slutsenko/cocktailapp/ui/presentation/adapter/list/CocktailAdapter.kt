package com.slutsenko.cocktailapp.ui.presentation.adapter.list

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.slutsenko.cocktailapp.entity.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.ui.presentation.adapter.list.CocktailAdapter.CocktailViewHolder
import com.slutsenko.cocktailapp.ui.activity.AboutCocktailActivity

class CocktailAdapter(private val context: Context, private var cocktailsList: List<Cocktail>)
    : RecyclerView.Adapter<CocktailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cocktail, parent, false)
        return CocktailViewHolder(view)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val currentCocktail = cocktailsList[position]
        val imageURL = currentCocktail.strDrinkThumb
        val cocktailName = currentCocktail.strDrink
        holder.cocktailImageName.text = cocktailName
        Glide.with(context)
                .load(imageURL)
                .centerCrop()
                .into(holder.cocktailImage)
    }

    override fun getItemCount(): Int {
        return cocktailsList.size
    }

    fun refreshData(list: List<Cocktail>) {
        cocktailsList = list
        notifyDataSetChanged()
    }

    inner class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cocktailImage: ImageView = itemView.findViewById(R.id.iv_cocktail)
        var cocktailImageName: TextView = itemView.findViewById(R.id.tv_cocktail_name)
        var favorite: CheckBox = itemView.findViewById(R.id.chb_favorite)

        init {
            favorite.setOnClickListener {
                val cockta = cocktailsList[adapterPosition]
                cockta.isFavorite = true
                Log.d("qwerty", " ${cockta.isFavorite}")
            }

            itemView.setOnLongClickListener { v: View? ->
                PopupMenu(context, v).apply {
                    // MainActivity implements OnMenuItemClickListener
                    setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.menu_item_open -> {
                                val intent = Intent(context, AboutCocktailActivity::class.java)
                                val cocktail = cocktailsList[adapterPosition]
                                intent.putExtra("cocktail", cocktail)
                                context.startActivity(intent)
                                true
                            }
                            else -> false
                        }

                    })
                    inflate(R.menu.menu_drink_item_shortcut)
                    show()
                }
                true
            }

            itemView.setOnClickListener { v: View? ->
                val intent = Intent(context, AboutCocktailActivity::class.java)
                val cocktail = cocktailsList[adapterPosition]
                intent.putExtra("cocktail", cocktail)
                context.startActivity(intent)
            }
        }
    }
}