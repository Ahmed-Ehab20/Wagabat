package com.fp.fproject.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.fp.fproject.R

class NextttActivity : AppCompatActivity() {

    private lateinit var skp3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_F_project)
        setContentView(R.layout.activity_nexttt)


        skp3 = findViewById(R.id.skp1)

        skp3.setOnClickListener{
            startActivity(
                Intent(
                    this@NextttActivity,
                    HomeScreenActivity::class.java
                )
            )
        }
    }
}