package com.slutsenko.cocktailapp.ui.presentation.adapter.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.base.BaseViewModel
import com.slutsenko.cocktailapp.db.CocktailDatabase
import com.slutsenko.cocktailapp.entity.Cocktail
import com.slutsenko.cocktailapp.ui.activity.AboutCocktailActivity
import com.slutsenko.cocktailapp.ui.fragment.MainFragment
import com.slutsenko.cocktailapp.ui.presentation.adapter.list.CocktailAdapter.CocktailViewHolder
import com.slutsenko.cocktailapp.viewmodel.MainViewModel

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

    interface OnFavoriteClick {
        fun refreshDB()
    }

    lateinit var favoriteCallback: OnFavoriteClick

    inner class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cocktailImage: ImageView = itemView.findViewById(R.id.iv_cocktail)
        var cocktailImageName: TextView = itemView.findViewById(R.id.tv_cocktail_name)
        var favorite: ImageView = itemView.findViewById(R.id.iv_favorite)

        init {
            favorite.setOnClickListener {
                val favoriteCocktail = cocktailsList[adapterPosition]
                if (favoriteCocktail.isFavorite == false) {
                    favoriteCocktail.isFavorite = true
                } else if (favoriteCocktail.isFavorite == true) {
                    favoriteCocktail.isFavorite = false
                }
                CocktailDatabase.getInstance(context)?.cocktailDao()?.addCocktail(favoriteCocktail)
                favoriteCallback.refreshDB()
            }

            itemView.setOnLongClickListener { v: View? ->
                PopupMenu(context, v).apply {
                    // MainActivity implements OnMenuItemClickListener
                    setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.menu_item_open -> {
                                val intent = Intent(context, AboutCocktailActivity::class.java)
                                val cocktail = cocktailsList[adapterPosition]
                                intent.putExtra("cocktail", cocktail)
                                context.startActivity(intent)
                                true
                            }
                            R.id.menu_add_favorite -> {
                                true
                            }
                            R.id.menu_remove_favorite -> {
                                true
                            }
                            else -> false
                        }
                    }
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