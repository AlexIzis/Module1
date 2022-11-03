package com.example.module1.news

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Parcelable
import com.example.module1.JsonParser
import com.example.module1.R
import java.util.ArrayList

class LoadNewsService : Service() {
    private lateinit var news: List<NewsUIModel>

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread.sleep(5000)
        news = JsonParser(
            getString(R.string.path_to_news),
            NewsUIModel::class.java,
            applicationContext
        ).parseJson()
        val categoryIntent = Intent().apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            action = "Load_News"
        }
        categoryIntent.putParcelableArrayListExtra("categories", news as ArrayList<out Parcelable>)
        sendBroadcast(categoryIntent)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}