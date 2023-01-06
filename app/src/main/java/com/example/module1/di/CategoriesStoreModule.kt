package com.example.module1.di

import android.content.Context
import com.example.module1.categories.CategoryStore
import com.example.module1.categories.CategoryStoreImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class CategoriesStoreModule {

    @Provides
    @Singleton
    fun provideCategoriesStore(context: Context): CategoryStore {
        return CategoryStoreImpl(context)
    }
}