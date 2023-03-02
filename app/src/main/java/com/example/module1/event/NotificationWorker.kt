package com.example.module1.event

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.module1.R

class NotificationWorker(
    val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        createChannel()
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.avatar_1)
            .setContentTitle("Hello World")
            .setContentText("This is your test notification")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
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
