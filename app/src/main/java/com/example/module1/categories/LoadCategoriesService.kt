package com.example.module1.categories

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.module1.JsonParser
import com.example.module1.R

class LoadCategoriesService : Service() {
    var categories : ArrayList<CategoryUiModel> = arrayListOf()
    private val binder = LocalBinder()

    fun loadData(): ArrayList<CategoryUiModel> {
        if (!ServiceThread().isAlive){
            ServiceThread().start()
        }
        return categories
    }

    /*override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!ServiceThread().isAlive){
            ServiceThread().start()
        }
        return START_NOT_STICKY
    }*/

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    private inner class ServiceThread : Thread() {
        override fun run() {
            sleep(5000)
            categories = JsonParser(
                getString(R.string.path_to_categories),
                CategoryUiModel::class.java,
                applicationContext
            ).parseJson() as java.util.ArrayList<CategoryUiModel>
        }
    }

    inner class LocalBinder : Binder(){
        fun getService() : LoadCategoriesService = this@LoadCategoriesService
    }
}