package com.fp.fproject.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.fp.fproject.R

class NexttActivity : AppCompatActivity() {
    private lateinit var skp2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_F_project)
        setContentView(R.layout.activity_nextt)

        skp2 = findViewById(R.id.skp1)

        skp2.setOnClickListener{
            startActivity(
                Intent(
                    this@NexttActivity,
                    NextttActivity::class.java
                )
            )
        }
    }
}