package com.example.module1.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.module1.*
import com.example.module1.categories.CategoriesFragment
import com.example.module1.news.NewsFragment
import com.example.module1.profile.ProfileFragment
import com.example.module1.search.MainSearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class CategoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        FragmentNavigation().addFragment(
            supportFragmentManager,
            R.id.fragmentContainerView,
            CategoriesFragment()
        )

        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        val navigation: BottomNavigationView = findViewById(R.id.btnNavHelp)
        navigation.selectedItemId = R.id.heart
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news -> {
                    FragmentNavigation().addFragment(
                        supportFragmentManager,
                        R.id.fragmentContainerView,
                        NewsFragment()
                    )
                    true
                }
                R.id.heart -> {
                    FragmentNavigation().addFragment(
                        supportFragmentManager,
                        R.id.fragmentContainerView,
                        CategoriesFragment()
                    )
                    true
                }
                R.id.search -> {
                    FragmentNavigation().addFragment(
                        supportFragmentManager,
                        R.id.fragmentContainerView,
                        MainSearchFragment()
                    )
                    true
                }
                R.id.history -> {
                    Toast.makeText(this, "History", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.profile -> {
                    FragmentNavigation().addFragment(
                        supportFragmentManager,
                        R.id.fragmentContainerView,
                        ProfileFragment()
                    )
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
