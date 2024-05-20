package com.fp.fproject.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fp.fproject.Activity.adapter.typAdapter
import com.fp.fproject.R
import typ

class DonatActivity : AppCompatActivity() {

    private lateinit var recyclerOne: RecyclerView
    private val crateItems: MutableList<typ> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_F_project)
        setContentView(R.layout.activity_donat)
        recyclerOne = findViewById(R.id.hor2)

        val typList = getTypList()
        val typAdapter = typAdapter(
            this@DonatActivity, // Context
            getTypList(), // typList, or replace with your actual typList if it's defined in the scope
            object : typAdapter.RecyclerViewClickListener {
                override fun onClick(v: View?, position: Int) {
                    // Handle click events if needed
                }

                override fun onAddToCrateClicked(position: Int) {
                    // Add the selected item to the crate list
                    val selectedItem = typList[position]
                    crateItems.add(selectedItem)                    }
            },
            mutableListOf() // crateItems, initialize an empty list or pass your existing list here
        )
        recyclerOne.apply {
            layoutManager = LinearLayoutManager(this@DonatActivity, LinearLayoutManager.VERTICAL, false)
            adapter = typAdapter
        }
    }

    private fun getTypList(): List<typ> {
        val typList: MutableList<typ> = ArrayList()
        typList.add(
            typ(
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
                "$5.50"
            )
        )
        typList.add(
            typ(
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
                "$9.50"
            )
        )
        typList.add(
            typ(
                "BAFALO BURGER",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "MEAT",
                "SANDWICHES",
                "COLD DRINKS",
                R.drawable.mc_feu,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$2.50"
            )
        )
        typList.add(
            typ(
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
                "$3.50"
            )
        )
        typList.add(
            typ(
                "COOK DOOR",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "CHICKEN",
                "SANDWICHES",
                "COLD DRINKS",
                R.drawable.mc_feu,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$2.50"
            )
        )
        typList.add(
            typ(
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
                "$4.50"
            )
        )
        typList.add(
            typ(
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
                "$5.50"
            )
        )
        typList.add(
            typ(
                "PIZZA KING",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "SALAD",
                "PIZZA",
                "COLD DRINKS",
                R.drawable.mc_feu,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$7.50"
            )
        )
        typList.add(
            typ(
                "MORE IN",
                "4.6",
                "(99+)",
                "$1 delivery",
                "5-15 mins",
                "PIZZA",
                "SAUCES",
                "COLD DRINKS",
                R.drawable.mc_feu,
                R.drawable.favourite_icon,
                R.drawable.verf_mc,
                "$9.50"
            )
        )
        typList.add(
            typ(
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
                "$12.50"
            )
        )
        return typList
    }
}