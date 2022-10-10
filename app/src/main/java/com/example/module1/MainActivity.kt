package com.example.module1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navigation: BottomNavigationView = findViewById(R.id.btnNav)
        navigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.news -> {
                    Toast.makeText(this, "news", Toast.LENGTH_LONG)
                    true
                }
                else -> {
                    Toast.makeText(this, "else", Toast.LENGTH_LONG)
                    true
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}