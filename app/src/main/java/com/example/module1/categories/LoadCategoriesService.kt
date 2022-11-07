package com.example.module1.categories

import android.app.Service
import android.content.Intent
import android.os.*
import com.example.module1.JsonParser
import com.example.module1.R
import kotlin.collections.ArrayList

class LoadCategoriesService : Service() {
    private val binder = LocalBinder()
    var categories: ArrayList<CategoryUiModel> = arrayListOf()

    fun printCategories(): ArrayList<CategoryUiModel> {
        if (!ServiceThread().isAlive){
            ServiceThread().start()
        }
        Thread.sleep(5000)
        return categories
    }

    private inner class ServiceThread : Thread() {
        override fun run() {
            categories =
                JsonParser(
                    getString(R.string.path_to_categories),
                    CategoryUiModel::class.java,
                    applicationContext
                ).parseJson() as ArrayList<CategoryUiModel>
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    inner class LocalBinder : Binder() {
        fun getService(): LoadCategoriesService = this@LoadCategoriesService
    }
}