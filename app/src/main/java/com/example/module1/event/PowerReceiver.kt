package com.example.module1.event

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PowerReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        when (p1?.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                Toast.makeText(p0, "connected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val customBroadcast: String = "ACTION_CUSTOM_BROADCAST"
    }
}
