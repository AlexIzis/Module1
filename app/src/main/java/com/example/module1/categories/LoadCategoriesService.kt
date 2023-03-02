package com.example.module1.categories

import android.app.Service
import android.content.Intent
import android.os.*
import androidx.annotation.MainThread
import com.example.module1.JsonParser
import com.example.module1.R
import java.lang.ref.WeakReference
import kotlin.collections.ArrayList

interface OnCategoriesCallback {

    fun onLoaded(categories: List<CategoryUiModel>)
}

class LoadCategoriesService : Service() {

    private val binder = LocalBinder()
    private var thread: ServiceThread? = null
    private var categories: List<CategoryUiModel>? = null
    private var onCategoriesCallback = WeakReference<OnCategoriesCallback>(null)
    private val mainHandler = Handler(Looper.getMainLooper())

    @MainThread
    fun printCategories(callback: WeakReference<OnCategoriesCallback>) {
        onCategoriesCallback = callback
        val categories = categories
        if (thread == null) {
            thread = ServiceThread()
            thread?.start()
        } else if (categories != null) {
            onCategoriesCallback.get()?.onLoaded(categories)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private inner class ServiceThread : Thread() {
        override fun run() {
            sleep(5000L)
            val categories =
                JsonParser(
                    getString(R.string.path_to_categories),
                    CategoryUiModel::class.java,
                    applicationContext
                ).parseJson() as ArrayList<CategoryUiModel>
            this@LoadCategoriesService.categories = categories
            mainHandler.post {
                onCategoriesCallback.get()?.onLoaded(categories)
            }
        }
    }

    inner class LocalBinder : Binder() {
        fun getService(): LoadCategoriesService = this@LoadCategoriesService
    }
}