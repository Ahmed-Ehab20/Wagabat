package com.fp.fproject.Activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fp.fproject.Activity.adapter.gridAdapter.gridViewHolder
import com.fp.fproject.R

class gridAdapter(var context: Context, var gridList: List<grid>) :
    RecyclerView.Adapter<gridViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): gridViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.grid_layout3, parent, false)
        return gridViewHolder(view)
    }

    override fun onBindViewHolder(holder: gridViewHolder, position: Int) {
        holder.images.setImageResource(gridList[position].images)
        holder.name.text = gridList[position].name
    }

    override fun getItemCount(): Int {
        return gridList.size
    }

    class gridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var images: ImageView
        var name: TextView

        init {
            images = itemView.findViewById(R.id.grid_img)
            name = itemView.findViewById(R.id.feedgridd_txt)
        }
    }
}