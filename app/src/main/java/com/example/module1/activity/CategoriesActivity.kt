package com.example.module1.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.module1.*
import com.example.module1.categories.CategoriesFragment
import com.example.module1.news.NewsFragment
import com.example.module1.profile.ProfileFragment
import com.example.module1.search.MainSearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class CategoriesActivity : AppCompatActivity() {

    private fun loadFragment(fr: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fr)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainerView, CategoriesFragment())

        val navigation: BottomNavigationView = findViewById(R.id.btnNavHelp)
        navigation.selectedItemId = R.id.heart
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news -> {
                    loadFragment(NewsFragment())
                    true
                }
                R.id.heart -> {
                    loadFragment(CategoriesFragment())
                    true
                }
                R.id.search -> {
                    loadFragment(MainSearchFragment())
                    true
                }
                R.id.history -> {
                    Toast.makeText(this, "History", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}
