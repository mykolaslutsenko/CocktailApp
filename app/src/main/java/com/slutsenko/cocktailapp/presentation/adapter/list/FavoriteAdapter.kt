package com.slutsenko.cocktailapp.presentation.adapter.list

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailModel
import com.slutsenko.cocktailapp.feature.detail.AboutCocktailActivity
import com.slutsenko.cocktailapp.feature.main.MainFragmentViewModel

class FavoriteAdapter(private val viewModel: MainFragmentViewModel? = null, private val context: Context, private var cocktailsList: List<CocktailModel>)
    : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_favorite_cocktail, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentCocktail = cocktailsList[position]
        val imageURL = currentCocktail.image
        val cocktailName = currentCocktail.names.def
        val type = currentCocktail.category.key
        val alcohol = currentCocktail.alcoholType.key
        val glass = currentCocktail.glass.key
        if (currentCocktail.isFavorite) {
            holder.favorite.setColorFilter(Color.YELLOW)
        } else if (!currentCocktail.isFavorite) {
            holder.favorite.setColorFilter(Color.WHITE)
        }
        holder.cocktailName.text = cocktailName
        holder.type.text = type
        holder.alcohol.text = alcohol
        holder.glass.text = glass
        Glide.with(context)
                .load(imageURL)
                .centerCrop()
                .into(holder.cocktailImage)
    }

    override fun getItemCount(): Int {
        return cocktailsList.size
    }

    fun updateList(newList: List<CocktailModel>) {
        val diffResult = DiffUtil.calculateDiff(
                FavoriteAdapter.FavoriteDiffUtil(cocktailsList, newList)
        )
        cocktailsList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class FavoriteDiffUtil(var oldList: List<CocktailModel>, var newList: List<CocktailModel>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].isFavorite == newList[newItemPosition].isFavorite
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].names == newList[newItemPosition].names
        }
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cocktailImage: ImageView = itemView.findViewById(R.id.iv_favorite_cocktail)
        var cocktailName: TextView = itemView.findViewById(R.id.txt_favorite_title)
        var favorite: ImageView = itemView.findViewById(R.id.iv_star_favorite)
        var type: TextView = itemView.findViewById(R.id.txt_favorite_type)
        var alcohol: TextView = itemView.findViewById(R.id.txt_favorite_alcohol)
        var glass: TextView = itemView.findViewById(R.id.txt_favorite_glass)
        var menu : ImageView = itemView.findViewById(R.id.iv_more_favorite)

        init {
            favorite.setOnClickListener {
                val favoriteCocktail = cocktailsList[adapterPosition]
                if (favoriteCocktail.isFavorite) {
                    removeFavorite()
                } else addFavorite()
            }


            menu.setOnClickListener { v: View? ->
                PopupMenu(context, v).apply {
                    // MainActivity implements OnMenuItemClickListener
                    setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.menu_item_open -> {
                                startAboutActivity()
                                true
                            }
                            R.id.menu_add_favorite -> {
                                addFavorite()
                                true
                            }
                            R.id.menu_remove_favorite -> {
                                removeFavorite()
                                true
                            }
                            R.id.menu_remove_cocktail -> {
                                deleteCocktail()
                                true
                            }
                            else -> false
                        }
                    }
                    inflate(R.menu.menu_drink_item_shortcut)
                    show()
                }
            }

            itemView.setOnClickListener { v: View? ->
                startAboutActivity()
            }
        }


        private fun startAboutActivity() {
            val intent = Intent(context, AboutCocktailActivity::class.java)
            val cocktail = cocktailsList[adapterPosition]
            intent.putExtra("cocktail_id_adapter", cocktail.id)
            context.startActivity(intent)
        }

        private fun deleteCocktail() {
            viewModel?.deleteCocktail(cocktailsList[adapterPosition])
        }

        private fun addFavorite() {
            val favoriteCocktail = cocktailsList[adapterPosition]
            if (!favoriteCocktail.isFavorite) {
                favoriteCocktail.isFavorite = true
            }
            viewModel?.saveToDb(favoriteCocktail)
        }

        private fun removeFavorite() {
            val favoriteCocktail = cocktailsList[adapterPosition]
            if (favoriteCocktail.isFavorite) {
                favoriteCocktail.isFavorite = false
            }
            viewModel?.saveToDb(favoriteCocktail)
        }
    }


}