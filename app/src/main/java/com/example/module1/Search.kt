package com.example.module1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Search : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val fragList = listOf(
            SearchFragment1.newInstance("firstFr", "SearchAct"),
            SearchFragment2.newInstance("SecondFr", "SearchAct")
        )

        val fragListTitle = listOf(
            "По мероприятиям",
            "По НКО"
        )

        val adapter = VpAdapter(this, fragList)
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val tb: TabLayout = findViewById(R.id.tabLayout)
        viewPager.adapter = adapter
        TabLayoutMediator(tb, viewPager) { item, pos ->
            item.text = fragListTitle[pos]
        }.attach()

        val navigation: BottomNavigationView = findViewById(R.id.btnNavHelp)
        navigation.selectedItemId = R.id.search
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
