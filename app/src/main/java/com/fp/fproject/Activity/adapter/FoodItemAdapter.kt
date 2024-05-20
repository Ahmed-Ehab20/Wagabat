package com.fp.fproject.Activity.adapter

import FoodItem
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fp.fproject.R

class FoodItemAdapter(private val foodItemList: List<FoodItem>,
                      private val context: Context,
                      ) :
    RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    inner class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var images: ImageView
        var fav_icon: ImageView
        var vrf_icon: ImageView
        var name: TextView
        var feed_txt: TextView
        var by_txt: TextView
        var dlv_txt: TextView
        var tm_mc: TextView
        var feu1: TextView
        var feu2: TextView
        var feu3: TextView



        init {
            images = itemView.findViewById(R.id.images)
            fav_icon = itemView.findViewById(R.id.fav_icon)
            vrf_icon = itemView.findViewById(R.id.vrf_icon)
            name = itemView.findViewById(R.id.name)
            feed_txt = itemView.findViewById(R.id.feed_txt)
            by_txt = itemView.findViewById(R.id.by_txt)
            dlv_txt = itemView.findViewById(R.id.dlv_txt)
            tm_mc = itemView.findViewById(R.id.tm_mc)
            feu1 = itemView.findViewById(R.id.feu1)
            feu2 = itemView.findViewById(R.id.feu2)
            feu3 = itemView.findViewById(R.id.feu3)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_inhorz, parent, false)
        return FoodItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        val currentItem = foodItemList[position]
        holder.name.text = currentItem.name
        holder.images.setImageResource(currentItem.imageResource ?: R.drawable.mc_feu)
        holder.fav_icon.setImageResource(currentItem.favoriteIcon ?: R.drawable.favourite_icon)
        holder.vrf_icon.setImageResource(currentItem.verificationIcon ?: R.drawable.verf_mc)
        holder.feed_txt.text = currentItem.name
        holder.by_txt.text = currentItem.rating
        holder.dlv_txt.text = currentItem.deliveryInfo
        holder.tm_mc.text = currentItem.deliveryTime
        holder.feu1.text = currentItem.category1
        holder.feu2.text = currentItem.category2
        holder.feu3.text = currentItem.category3
    }
    override fun getItemCount() = foodItemList.size

    }



