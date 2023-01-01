package com.example.module1.di

import com.example.module1.categories.CategoryStoreImpl
import dagger.Component

@Component(modules = [CategoriesStoreModule::class])
interface CategoriesComponent {
    fun getCatStore(): CategoryStoreImpl
}