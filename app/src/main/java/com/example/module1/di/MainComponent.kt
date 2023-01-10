package com.example.module1.di

import com.example.module1.categories.CategoriesFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [StoreModule::class, VMFactoryModule::class])
@Singleton
interface MainComponent {
    fun inject(categoriesFragment: CategoriesFragment)
}