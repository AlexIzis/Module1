package com.example.module1

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, CategoriesFragment())
        fragmentTransaction.commit()

        val navigation: BottomNavigationView = findViewById(R.id.btnNavHelp)
        navigation.selectedItemId = R.id.heart
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news -> {
                    Toast.makeText(this, "News", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.heart -> {
                    true
                }
                R.id.search -> {
                    Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.history -> {
                    Toast.makeText(this, "History", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.profile -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}
