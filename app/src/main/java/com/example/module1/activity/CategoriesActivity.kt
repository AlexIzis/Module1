package com.example.module1.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.module1.*
import com.example.module1.auth.LoginFragment
import com.example.module1.categories.CategoriesFragment
import com.example.module1.news.NewsBus
import com.example.module1.news.NewsFragment
import com.example.module1.news.NewsUIModel
import com.example.module1.profile.ProfileFragment
import com.example.module1.search.MainSearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

private const val LOAD_KEY = "load_key"

class CategoriesActivity : AppCompatActivity() {
    private var countAllNews = 0
    private var readNews = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        if (savedInstanceState == null) {
            FragmentNavigation().addFragment(
                supportFragmentManager,
                R.id.fragmentContainerView,
                LoginFragment()
            )
        }

        countAllNews = JsonParser(
            getString(R.string.path_to_news),
            NewsUIModel::class.java,
            applicationContext
        ).parseJson().size

        val navigation: BottomNavigationView = findViewById(R.id.btnNavHelp)
        navigation.getOrCreateBadge(R.id.news).number = countAllNews - readNews

        NewsBus.listen().subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                navigation.getOrCreateBadge(R.id.news).number = countAllNews - it.toInt()
            }

        navigation.selectedItemId = R.id.heart
        navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news -> {
                    FragmentNavigation().replaceFragment(
                        supportFragmentManager,
                        R.id.fragmentContainerView,
                        NewsFragment()
                    )
                    true
                }
                R.id.heart -> {
                    FragmentNavigation().replaceFragment(
                        supportFragmentManager,
                        R.id.fragmentContainerView,
                        CategoriesFragment()
                    )
                    true
                }
                R.id.search -> {
                    FragmentNavigation().replaceFragment(
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
                    FragmentNavigation().replaceFragment(
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(LOAD_KEY, true)
    }
}
