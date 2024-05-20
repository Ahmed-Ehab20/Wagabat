package com.fp.fproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import com.fp.fproject.R

class DetailsActivity : AppCompatActivity() {
    private lateinit var back_sp: ImageButton
    private lateinit var minus: ImageButton
    private lateinit var plus: ImageButton
    private lateinit var chk1: RadioButton
    private lateinit var chk2: RadioButton
    private lateinit var chk3: RadioButton
    private lateinit var addTo: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_F_project)
        setContentView(R.layout.activity_details)

        back_sp = findViewById(R.id.back)
        back_sp.setOnClickListener{
            onBackPressed()
        }

        var name = "Name not set"
        val extras = intent.extras
        if (extras != null) {
            name = extras.getString("name").toString()
        }


    }
}