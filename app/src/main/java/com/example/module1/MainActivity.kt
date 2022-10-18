package com.example.module1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pencilButton: ImageView = findViewById(R.id.imageView4)
        pencilButton.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }

        val navigation: BottomNavigationView = findViewById(R.id.btnNav)
        navigation.selectedItemId = R.id.profile
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news -> {
                    Toast.makeText(this, "News", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.heart -> {
                    val intent = Intent(this, Categories::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.search -> {
                    val intent = Intent(this, Search::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.history -> {
                    Toast.makeText(this, "History", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.profile -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}
