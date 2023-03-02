package com.example.module1.event

import android.content.Context
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    private val context = context
    override fun doWork(): Result {
        Toast.makeText(context, "work manager", Toast.LENGTH_SHORT).show()
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Hello World")
            .setContentText("This is your test notification")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_ID, builder)
        return Result.success()
    }

    companion object {
        const val CHANNEL_ID = "channelID"
        const val NOTIFICATION_ID = 101
    }
}
