package com.example.module1

import android.app.Application
import com.example.module1.di.CategoriesComponent
import com.example.module1.di.ContextModule
import com.example.module1.di.DaggerCategoriesComponent

class AppInst : Application() {

    lateinit var categoriesComponent: CategoriesComponent

    override fun onCreate() {
        super.onCreate()
        categoriesComponent = DaggerCategoriesComponent.builder()
            .contextModule(ContextModule(context = this))
            .build()
    }
}