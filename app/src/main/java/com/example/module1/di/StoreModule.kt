package com.example.module1.di

import android.content.Context
import com.example.module1.categories.CategoryStore
import com.example.module1.categories.CategoryStoreImpl
import com.example.module1.news.NewsStore
import com.example.module1.news.NewsStoreImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class StoreModule {

    @Provides
    @Singleton
    fun provideCategoriesStore(context: Context): CategoryStore {
        return CategoryStoreImpl(context)
    }

    @Provides
    @Singleton
    fun provideNewsStore(context: Context): NewsStore {
        return NewsStoreImpl(context = context)
    }
}