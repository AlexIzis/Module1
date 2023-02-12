package com.example.module1.di

import com.example.module1.categories.CategoriesFragment
import com.example.module1.filter.FilterFragment
import com.example.module1.news.NewsComposeActivity
import com.example.module1.news.NewsFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [StoreModule::class, VMFactoryModule::class])
@Singleton
interface MainComponent {
    fun injectCategoriesFragment(categoriesFragment: CategoriesFragment)
    fun injectNewsFragment(newsFragment: NewsFragment)
    fun injectFilterFragment(filterFragment: FilterFragment)
    fun injectNewsComposeActivity(newsComposeActivity: NewsComposeActivity)
}