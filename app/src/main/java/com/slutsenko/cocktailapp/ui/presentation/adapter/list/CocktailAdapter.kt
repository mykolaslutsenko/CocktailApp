package com.slutsenko.cocktailapp.ui.presentation.adapter.list

import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.slutsenko.cocktailapp.Cocktail
import com.slutsenko.cocktailapp.R
import com.slutsenko.cocktailapp.ui.presentation.adapter.list.CocktailAdapter.CocktailViewHolder
import com.slutsenko.cocktailapp.ui.AboutCocktailActivity
import java.nio.file.Files.delete
import java.util.*

class CocktailAdapter(private val context: Context, private val cocktailsList: ArrayList<Cocktail>)
    : RecyclerView.Adapter<CocktailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cocktail, parent, false)
        return CocktailViewHolder(view)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val currentCocktail = cocktailsList[position]
        val imageURL = currentCocktail.strDrinkThumb
        val cocktailName = currentCocktail.strDrink
        holder.tv_cocktail_name.text = cocktailName
        Glide.with(context)
                .load(imageURL)
                .centerCrop()
                .into(holder.iv_cocktail)
    }

    override fun getItemCount(): Int {
        return cocktailsList.size
    }

    inner class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_cocktail: ImageView = itemView.findViewById(R.id.iv_cocktail)
        var tv_cocktail_name: TextView = itemView.findViewById(R.id.tv_cocktail_name)

        init {
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

