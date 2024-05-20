package com.fp.fproject.Activity

import FoodItem
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fp.fproject.Activity.adapter.FoodItemAdapter
import com.fp.fproject.Activity.adapter.typAdapter
import com.fp.fproject.R
import typ

class AllRest : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodItemList: List<FoodItem> // Use the global variable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_F_project)
        setContentView(R.layout.activity_allrest)

        recyclerView = findViewById(R.id.view_allrest)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@AllRest, LinearLayoutManager.VERTICAL, false)
        }
        // Call getFoodItemList to initialize the global variable
        foodItemList = getFoodItemList()

        // Pass the foodItemList to the adapter
        val FoodAdapter = FoodItemAdapter(
            foodItemList,
            this
        )

        recyclerView.adapter = FoodAdapter // Set the adapter to the RecyclerView
    }

    private fun getFoodItemList(): List<FoodItem> {
        val foodItem: MutableList<FoodItem> = ArrayList()
        foodItem.add(
            FoodItem(
                "ABO AMAAR",
                "4.5",
                "(25+)",
                "Free delivery",
                "10-15 mins",
                "SHAWRMA",
                "SYRIAN FOOD",
                "FAMILY MEALS",
                R.drawable.mc_feu,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$9.50"
            )
        )
        foodItem.add(
            FoodItem(
                "BAZOOKA",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "FRIED CHICKEN",
                "SANDWICHES",
                "COLD DRINKS",
                R.drawable.kfc_ic1,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$5.50"

            )
        )
        foodItem.add(
            FoodItem(
                "BAFALO BURGER",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "MEAT",
                "SANDWICHES",
                "COLD DRINKS",
                R.drawable.kfc_ic1,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$15"

            )
        )
        foodItem.add(
            FoodItem(
                "BURGER REPUBLIC",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "BURGER",
                "SANDWICHES",
                "COLD DRINKS",
                R.drawable.kfc_ic1,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$20"

            )
        )
        foodItem.add(
            FoodItem(
                "COOK DOOR",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "CHICKEN",
                "SANDWICHES",
                "COLD DRINKS",
                R.drawable.stb_img,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$10.99S"

            )
        )
        foodItem.add(
            FoodItem(
                "KARAM AL SHAM",
                "4.7",
                "(99+)",
                "$2 delivery",
                "10-15 mins",
                "SYRIAN FOOD",
                "SHAWRMA",
                "FAST FOOD",
                R.drawable.mc_feu,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$5.50"

            )
        )
        foodItem.add(
            FoodItem(
                "MO'MEN",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "SANDWICHES",
                "CHICKEN",
                "COLD DRINKS",
                R.drawable.kfc_ic1,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$25.50"

            )
        )
        foodItem.add(
            FoodItem(
                "PIZZA KING",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "SALAD",
                "PIZZA",
                "COLD DRINKS",
                R.drawable.stb_img,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$17.50"

            )
        )
        foodItem.add(
            FoodItem(
                "MORE IN",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "PIZZA",
                "SAUCES",
                "COLD DRINKS",
                R.drawable.stb_img,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$9.50"

            )
        )
        foodItem.add(
            FoodItem(
                "ZACK'S",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "CHICKEN",
                "SANDWICHES",
                "COLD DRINKS",
                R.drawable.kfc_ic1,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$2.50"

            )
        )
        return foodItem
    }



}