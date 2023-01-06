package com.example.module1.di

import com.example.module1.categories.CategoriesViewModelFactory
import com.example.module1.categories.CategoryStore
import dagger.Module
import dagger.Provides

@Module
class VMFactoryModule {

    @Provides
    fun provideVMFactory(categoryStore: CategoryStore): CategoriesViewModelFactory {
        return CategoriesViewModelFactory(categoryStore = categoryStore)
    }
}