package com.example.module1.di

import android.content.Context
import com.example.module1.categories.CategoryStoreImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class CategoriesStoreModule {

    @Provides
    fun provideCategoriesStore(context: Context): CategoryStoreImpl {
        return CategoryStoreImpl(context)
    }
}