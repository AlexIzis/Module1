package com.example.module1.event

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.module1.R
import com.example.module1.news.NewsComposeActivity

class NotificationWorker(
    val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        createChannel()
        val intent = Intent(context, NewsComposeActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.avatar_1)
            .setContentTitle(inputData.getString("name"))
            .setContentText("Спасибо!!! ")
            .addAction(R.drawable.ic_access_time, "Напомнить позже", pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(
                    "Спасибо, что пожертвовали ${inputData.getString("sum")} ₽! " +
                        "Будем очень признательны, если вы сможете пожертвовать еще больше."
                )
            )
            .setAutoCancel(true)
            .build()
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_ID, builder)
        return Result.success()
    }

    private fun createChannel() {
        val channel = NotificationChannel(CHANNEL_ID, "test", NotificationManager.IMPORTANCE_HIGH)
        channel.description = "This is test channel"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val CHANNEL_ID = "channelID"
        const val NOTIFICATION_ID = 101
    }
}
