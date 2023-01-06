package com.example.module1.di

import com.example.module1.categories.CategoriesFragment
import com.example.module1.categories.CategoryStore
import com.example.module1.categories.CategoryStoreImpl
import dagger.Component
import javax.inject.Singleton

@Component(modules = [CategoriesStoreModule::class, VMFactoryModule::class])
@Singleton
interface CategoriesComponent {
    fun inject(categoriesFragment: CategoriesFragment)
}