package com.fp.fproject.Activity.adapter

import com.fp.fproject.Activity.adapter.typAdapter.RecyclerViewClickListener
import androidx.recyclerview.widget.RecyclerView
import com.fp.fproject.Activity.adapter.typAdapter.typViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.fp.fproject.R
import android.content.Intent
import com.fp.fproject.Activity.DetailsActivity
import android.widget.TextView
import com.fp.fproject.Activity.adapter.typAdapter
import com.fp.fproject.Activity.adapter.grid
import com.fp.fproject.Activity.adapter.gridAdapter.gridViewHolder
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues

data class grid(var name: String, var images: Int)