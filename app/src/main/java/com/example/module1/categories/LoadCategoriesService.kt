package com.example.module1.categories

import android.app.Service
import android.content.Intent
import android.os.*
import com.example.module1.JsonParser
import com.example.module1.R
import kotlin.collections.ArrayList

class LoadCategoriesService : Service() {
    //var categories: ArrayList<CategoryUiModel> = arrayListOf()
    private val binder = LocalBinder()

    fun printHello(): ArrayList<CategoryUiModel> {
        return JsonParser(
            getString(R.string.path_to_categories),
            CategoryUiModel::class.java,
            applicationContext
        ).parseJson() as ArrayList<CategoryUiModel>
    }


    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    inner class LocalBinder : Binder() {
        fun getService(): LoadCategoriesService = this@LoadCategoriesService
    }
}