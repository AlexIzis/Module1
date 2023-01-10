package com.example.module1

import android.app.Application
import com.example.module1.di.ContextModule
import com.example.module1.di.DaggerMainComponent
import com.example.module1.di.MainComponent

class AppClass : Application() {

    lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder()
            .contextModule(ContextModule(context = this))
            .build()
    }
}