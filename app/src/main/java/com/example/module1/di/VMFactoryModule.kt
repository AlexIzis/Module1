package com.example.module1.di

import com.example.module1.categories.CategoriesViewModelFactory
import com.example.module1.categories.CategoryStore
import com.example.module1.news.NewsStore
import com.example.module1.news.NewsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class VMFactoryModule {

    @Provides
    fun provideCategoriesVMFactory(categoryStore: CategoryStore): CategoriesViewModelFactory {
        return CategoriesViewModelFactory(categoryStore = categoryStore)
    }

    @Provides
    fun provideNewsVMFactory(newsStore: NewsStore): NewsViewModelFactory {
        return NewsViewModelFactory(newsStore = newsStore)
    }
}