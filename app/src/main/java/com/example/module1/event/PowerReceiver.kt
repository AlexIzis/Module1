package com.example.module1.event

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class PowerReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        when (p1?.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
                val data = Data.Builder()
                data.putString("id", "1")
                data.putString("name", "event name")
                data.putString("sum", "10")
                workRequest.setInputData(data.build())
                WorkManager.getInstance(requireNotNull(p0)).enqueue(workRequest.build())
            }
        }
    }
}
