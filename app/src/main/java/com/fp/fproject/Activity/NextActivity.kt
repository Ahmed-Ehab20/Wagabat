package com.fp.fproject.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.fp.fproject.R

class NextActivity : AppCompatActivity() {

    private lateinit var skp1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_F_project)
        setContentView(R.layout.activity_next)

        skp1 = findViewById(R.id.skp1)

        skp1.setOnClickListener{
            startActivity(
                Intent(
                    this@NextActivity,
                    NexttActivity::class.java
                )
            )
        }
    }
}