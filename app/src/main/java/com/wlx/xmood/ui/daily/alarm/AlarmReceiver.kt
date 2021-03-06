package com.wlx.xmood.ui.daily.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.wlx.xmood.MainActivity
import com.wlx.xmood.R

class AlarmReceiver : BroadcastReceiver() {

    private val TAG = "AlarmReceiver"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive: ${intent.toString()}")
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notificationLayout = RemoteViews("com.wlx.xmood", R.layout.daily_alarm_notification)
        val toDaily = Intent(context, MainActivity::class.java).apply {
            putExtra("target", "daily")
        }
        val pi = PendingIntent.getActivity(context, 0, toDaily, 0)
        val notification = NotificationCompat.Builder(
            context,
            createChannel(notificationManager, "daily", "Xmood", 3)
        )
            .setSmallIcon(R.drawable.ic_daily_24)
            .setContentTitle("XMood日程提醒")
            .setContentText(intent.getStringExtra("event"))
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(intent.getIntExtra("id", 0), notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(
        manager: NotificationManager,
        channelId: String,
        channelName: String,
        level: Int
    ): String {
        val channel = NotificationChannel(channelId, channelName, level)
        manager.createNotificationChannel(channel)
        return channelId
    }
}