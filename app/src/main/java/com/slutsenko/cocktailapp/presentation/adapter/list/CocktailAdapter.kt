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
import com.slutsenko.cocktailapp.presentation.adapter.list.CocktailAdapter.CocktailViewHolder
import com.slutsenko.cocktailapp.presentation.model.cocktail.CocktailModel
import com.slutsenko.cocktailapp.presentation.ui.activity.AboutCocktailActivity
import com.slutsenko.cocktailapp.presentation.viewmodel.MainFragmentViewModel

class CocktailAdapter(private val viewModel: MainFragmentViewModel? = null, private val context: Context, private var cocktailsList: List<CocktailModel>)
    : RecyclerView.Adapter<CocktailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cocktail, parent, false)
        return CocktailViewHolder(view)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val currentCocktail = cocktailsList[position]
        val imageURL = currentCocktail.image
        val cocktailName = currentCocktail.names.def
        if (currentCocktail.isFavorite) {
            holder.favorite.setColorFilter(Color.YELLOW)
        } else if (!currentCocktail.isFavorite) {
            holder.favorite.setColorFilter(Color.WHITE)
        }
        holder.cocktailImageName.text = cocktailName
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
                HistoryDiffUtil(cocktailsList, newList)
        )
        cocktailsList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class HistoryDiffUtil(var oldList: List<CocktailModel>, var newList: List<CocktailModel>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].isFavorite == newList[newItemPosition].isFavorite
        }
    }


//    public void updateList(ArrayList<Person> newList) {
//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(this.persons, newList));
//        diffResult.dispatchUpdatesTo(this);
//    }

//    fun refreshData(list: List<CocktailModel>) {
//        cocktailsList = list
//        notifyDataSetChanged()
//    }

    inner class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cocktailImage: ImageView = itemView.findViewById(R.id.iv_cocktail)
        var cocktailImageName: TextView = itemView.findViewById(R.id.tv_cocktail_name)
        var favorite: ImageView = itemView.findViewById(R.id.iv_favorite)

        init {
            favorite.setOnClickListener {
                val favoriteCocktail = cocktailsList[adapterPosition]
                if (favoriteCocktail.isFavorite) {
                    removeFavorite()
                } else addFavorite()

            }


            itemView.setOnLongClickListener { v: View? ->
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
                true
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