package com.fp.fproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fp.fproject.Activity.adapter.typAdapter
import com.fp.fproject.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import typ

class CrateActivity : AppCompatActivity(), typAdapter.RecyclerViewClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var checkoutButton: ImageButton
    private var crateItems: MutableList<typ> = mutableListOf()
    private lateinit var typList: List<typ>
    private lateinit var listener: typAdapter.RecyclerViewClickListener
    private lateinit var adapter: typAdapter // Declare the adapter variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_F_project)
        setContentView(R.layout.activity_crate)

        recyclerView = findViewById(R.id.order_view)
        recyclerView.layoutManager = LinearLayoutManager(this)


        // Initialize checkoutButton
        checkoutButton = findViewById(R.id.checkoutButton)



        // Retrieve crateItems list from intent

        // Log the size of the crateItems list
        Log.d("CrateActivity", "Size of crateItems list: ${crateItems.size}")

        // Populate typList and crateItems
        typList = getTypList()

        // Initialize listener
        listener = this

        // Initialize adapter with crateItems
        adapter = typAdapter(this, typList, listener, crateItems)

        // Check if adapter is initialized
        if (::adapter.isInitialized) {
            // Set adapter to RecyclerView
            recyclerView.adapter = adapter
        } else {
            Log.e("CrateActivity", "Adapter is not initialized")
        }

        // Set up checkout button click listener
        checkoutButton.setOnClickListener {
            if (isLoggedIn() && isValidCrate(crateItems)) {
                // Proceed with checkout
                Toast.makeText(this, "Order Has Confirmed", Toast.LENGTH_SHORT).show()
            } else {
                // Show error message or prompt login if not logged in
                Toast.makeText(
                    this,
                    "Please login and add valid items to the crate",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Function to get typList (example)
    private fun getTypList(): List<typ> {
        // Implementation to get typList
        return listOf(/* Your typ items */)
    }

    private fun isLoggedIn(): Boolean {
        // Check if the user is logged in
        // Return true if logged in, false otherwise
        return false // Placeholder implementation
    }

    private fun isValidCrate(crateItems: List<typ>): Boolean {
        // Check if the crate has valid items for checkout
        // For example, check if the items are not empty and total price is valid
        return crateItems.isNotEmpty() // Placeholder implementation
    }

    override fun onClick(v: View?, position: Int) {
        // Handle click events if needed
    }

    override fun onAddToCrateClicked(position: Int) {
        // Add the selected item to the crate list
        val selectedItem = typList[position]
        crateItems.add(selectedItem)
        // Update the adapter with the new crateItems list
        adapter.updateCrateItems(crateItems)
        // Retrieve JSON string extra from Intent
        val selectedItemsJson = intent.getStringExtra("selectedItemsJson")

        // Deserialize JSON string back to list of FoodItem
        val gson = Gson()
        val itemType = object : TypeToken<MutableList<typ>>() {}.type
        val selectedItems = gson.fromJson<MutableList<typ>>(selectedItemsJson, itemType)

    }
}
