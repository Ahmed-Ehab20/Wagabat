package com.fp.fproject.Activity.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.fp.fproject.R
import android.widget.TextView
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import typ


class typAdapter(
    private val context: Context,
    private val typList: List<typ>,
    private val listener: RecyclerViewClickListener,
    mutableListOf: MutableList<typ>,
) : RecyclerView.Adapter<typAdapter.typViewHolder>() {
    private var crateItems: MutableList<typ> = mutableListOf()

    // Function to update crateItems
    fun updateCrateItems(crateItems: List<typ>) {
        this.crateItems.clear()
        this.crateItems.addAll(crateItems)
        notifyDataSetChanged()
        Log.d("typAdapter", "Updated crateItems size: ${crateItems.size}")
    }

    inner class typViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
        val addToCart: Button = itemView.findViewById(R.id.addToCart)



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

            itemView.tag = adapterPosition
            // Set click listener using the extension function
            addToCart.setOnClickListenerWithPosition { position ->
                // Handle click event here
                listener.onClick(itemView, position)
            }

         fun onClick(v: View) {
            listener.onClick(v, absoluteAdapterPosition)
        }
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): typViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_row_one, parent, false)
        return typViewHolder(view)
    }

    override fun onBindViewHolder(holder: typViewHolder, position: Int) {
        val currentItem = typList[position]
        holder.name.text = currentItem.name
        holder.images.setImageResource(currentItem.imageResource ?: R.drawable.mc_feu)
        holder.fav_icon.setImageResource(currentItem.favoriteIcon ?: R.drawable.favourite_icon)
        holder.vrf_icon.setImageResource(currentItem.verificationIcon ?: R.drawable.verf_mc)
        holder.feed_txt.text = currentItem.rating
        holder.by_txt.text = currentItem.reviews
        holder.dlv_txt.text = currentItem.deliveryInfo
        holder.tm_mc.text = currentItem.deliveryTime
        holder.feu1.text = currentItem.category1
        holder.feu2.text = currentItem.category2
        holder.feu3.text = currentItem.category3

        // Check if the current item is in the crateItems list
        val isInCrate = crateItems.contains(currentItem)
        // Change the appearance of addToCart button based on whether the item is in the crate
        if (isInCrate) {
            holder.addToCart.text = context.getString(R.string.remove_from_crate)
        } else {
            holder.addToCart.text = context.getString(R.string.add_to_crate)
        }
        holder.addToCart.setOnClickListener {
            if (isInCrate) {
                // Remove the item from the crate
                crateItems.remove(currentItem)
                holder.addToCart.text = context.getString(R.string.add_to_crate)
                Toast.makeText(context, "Item removed from crate", Toast.LENGTH_SHORT).show()
            } else {
                // Add the item to the crate
                crateItems.add(currentItem)
                holder.addToCart.text = context.getString(R.string.remove_from_crate)
                Toast.makeText(context, "Item added to crate", Toast.LENGTH_SHORT).show()
            }
        }

    }


    override fun getItemCount(): Int {
        return typList.size
    }

    interface RecyclerViewClickListener {
        fun onClick(v: View?, position: Int)
        fun onAddToCrateClicked(position: Int)
    }
    fun View.setOnClickListenerWithPosition(clickListener: (position: Int) -> Unit) {
        this.setOnClickListener {
            clickListener.invoke(it.tag as Int)
        }
    }
    }



//    class typViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
//        var images: ImageView
//        var fav_icon: ImageView
//        var vrf_icon: ImageView
//        var name: TextView
//        var feed_txt: TextView
//        var by_txt: TextView
//        var dlv_txt: TextView
//        var tm_mc: TextView
//        var feu1: TextView
//        var feu2: TextView
//        var feu3: TextView
//        val price: TextView = itemView.findViewById(R.id.price)
//        val addToCart: Button = itemView.findViewById(R.id.addToCart)
//
//        init {
//            images = itemView.findViewById(R.id.images)
//            fav_icon = itemView.findViewById(R.id.fav_icon)
//            vrf_icon = itemView.findViewById(R.id.vrf_icon)
//            name = itemView.findViewById(R.id.name)
//            feed_txt = itemView.findViewById(R.id.feed_txt)
//            by_txt = itemView.findViewById(R.id.by_txt)
//            dlv_txt = itemView.findViewById(R.id.dlv_txt)
//            tm_mc = itemView.findViewById(R.id.tm_mc)
//            feu1 = itemView.findViewById(R.id.feu1)
//            feu2 = itemView.findViewById(R.id.feu2)
//            feu3 = itemView.findViewById(R.id.feu3)
//            itemView.setOnClickListener(this)
//        }
//
//        override fun onClick(v: View) {
//            listener.onClick(v, absoluteAdapterPosition)
//        }
//    }



//    override fun onBindViewHolder(holder: typViewHolder, position: Int) {
//        val currentItem = typList[position]
//        holder.name.text = currentItem.name
//        holder.feedTxt.text = currentItem.feed_txt
//        holder.byTxt.text = currentItem.by_txt
//        holder.dlvTxt.text = currentItem.dlv_txt
//        holder.tmMc.text = currentItem.tm_mc
//        holder.feu1.text = currentItem.feu1
//        holder.feu2.text = currentItem.feu2
//        holder.feu3.text = currentItem.feu3
//        holder.images.setImageResource(currentItem.images)
//        holder.favIcon.setImageResource(currentItem.fav_icon)
//        holder.vrfIcon.setImageResource(currentItem.vrf_icon)
//
//        holder.addToCart.setOnClickListener {
//            // Add currentItem to the crate or perform any other action
//            Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show()
//        }
//    }

//    override fun onBindViewHolder(holder: typViewHolder, position: Int) {
//        val currentItem = typList[position]
//        holder.images.setImageResource(typList[position].images)
//        holder.fav_icon.setImageResource(typList[position].fav_icon)
//        holder.vrf_icon.setImageResource(typList[position].vrf_icon)
//        holder.name.text = typList[position].name
//        holder.feed_txt.text = typList[position].feed_txt
//        holder.by_txt.text = typList[position].by_txt
//        holder.dlv_txt.text = typList[position].dlv_txt
//        holder.tm_mc.text = typList[position].tm_mc
//        holder.feu1.text = typList[position].feu1
//        holder.feu2.text = typList[position].feu2
//        holder.feu3.text = typList[position].feu3
//        holder.itemView.setOnClickListener {
//            val i = Intent(context, DetailsActivity::class.java)
//            context.startActivity(i)
//        }
//        holder.price.text = currentItem.price.toString()
//        holder.addToCart.setOnClickListener {
//            // Add currentItem to the crate
//            crateItems.add(currentItem)
//            Toast.makeText(context, "Item added to crate", Toast.LENGTH_SHORT).show()
//        }
//    }



