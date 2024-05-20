package com.fp.fproject.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fp.fproject.R

class FavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_F_project)
        setContentView(R.layout.activity_fav)
    }
}