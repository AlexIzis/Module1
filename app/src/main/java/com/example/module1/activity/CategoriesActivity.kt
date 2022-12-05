package com.example.module1.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.module1.FragmentNavigation
import com.example.module1.JsonParser
import com.example.module1.R
import com.example.module1.VMNewsFlow
import com.example.module1.categories.CategoriesFragment
import com.example.module1.news.NewsFlow
import com.example.module1.news.NewsFragment
import com.example.module1.news.NewsUIModel
import com.example.module1.profile.ProfileFragment
import com.example.module1.retrofit.Request
import com.example.module1.search.MainSearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CategoriesActivity : AppCompatActivity() {
    private var countAllNews = 0
    private lateinit var disposable: Disposable
    private lateinit var navigation: BottomNavigationView
    private val viewModel: VMNewsFlow by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        if (savedInstanceState == null) {
            FragmentNavigation().addFragment(
                supportFragmentManager,
                R.id.fragmentContainerView,
                CategoriesFragment()
            )
        }

        Request().startRequest()

        navigation = findViewById(R.id.btnNavHelp)
        disposable = Observable.fromCallable {
            JsonParser(
                getString(R.string.path_to_news),
                NewsUIModel::class.java,
                applicationContext
            ).parseJson().size
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                countAllNews = it
                navigation.getOrCreateBadge(R.id.news).number = countAllNews
            }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                viewModel.flow.collect{
                    navigation.getOrCreateBadge(R.id.news).number = countAllNews - it
                }
            } catch (e: Exception) {
                Log.d("tag", e.toString())
                Log.d("tag", "Программка, не болей")
            }
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

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
