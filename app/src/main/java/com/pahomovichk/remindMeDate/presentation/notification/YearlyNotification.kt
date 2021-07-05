package com.pahomovichk.remindMeDate.presentation.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.cleanarchitechture.App
import com.pahomovichk.remindMeDate.R
import kotlinx.coroutines.*
import com.pahomovichk.remindMeDate.domain.entity.Event


class YearlyNotification() {

    companion object {
        const val CHANNEL_ID = "YEARLY_NOTIFICATION_CHANNEL"
        const val CHANNEL_NAME = "EventsNotificationChannel"
        const val NOTIFICATION_ID = 1
        const val NOTIFICATION_TITLE = "NEW EVENT!"
        const val EVENTS_NOTIFICATION_CHANNEL_NAME = "Events Notification Channel"
    }

    private val ioScope = CoroutineScope(Dispatchers.IO + Job())

    fun showNotification(context: Context, event: Event) {
        ioScope.launch {
            createNotificationChannel()
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText("today you have ${event.getEventName()} ${event.getEventType()}!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .build()
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(NOTIFICATION_ID, builder)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val descriptionText = "The user have some events today."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, EVENTS_NOTIFICATION_CHANNEL_NAME, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                App.instance.getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}