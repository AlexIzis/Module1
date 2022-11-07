package com.example.module1.news

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Parcelable
import com.example.module1.JsonParser
import com.example.module1.R
import java.util.ArrayList

const val INTENT_FILTER_ACTION = "Load_News"
const val KEY_FROM_NEWS_SERVICE = "from_service"

class LoadNewsService : Service() {
    private lateinit var news: List<NewsUIModel>

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!ServiceThread().isAlive){
            ServiceThread().start()
        }
        return START_NOT_STICKY
    }

    private inner class ServiceThread : Thread() {
        override fun run() {
            sleep(5000)
            news = JsonParser(
                getString(R.string.path_to_news),
                NewsUIModel::class.java,
                applicationContext
            ).parseJson()
            val categoryIntent = Intent().apply {
                addCategory(Intent.CATEGORY_DEFAULT)
                action = INTENT_FILTER_ACTION
            }
            categoryIntent.putParcelableArrayListExtra(KEY_FROM_NEWS_SERVICE, news as ArrayList<out Parcelable>)
            sendBroadcast(categoryIntent)
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}