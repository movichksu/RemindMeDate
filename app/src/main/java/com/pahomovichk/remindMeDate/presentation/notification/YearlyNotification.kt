package com.pahomovichk.remindMeDate.presentation.notification

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.cleanarchitechture.App
import com.pahomovichk.remindMeDate.Dependencies
import com.pahomovichk.remindMeDate.R
import com.pahomovichk.remindMeDate.domain.YearlyEventsUseCase
import kotlinx.coroutines.*
import android.content.ContextWrapper
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent


class YearlyNotification() {

    companion object {
        const val TAG = "PersonService"
        const val CHANNEL_ID = "YEARLY_CHANNEL"
        const val NOTIFICATION_ID = 1
        const val notificationTitle = "NEW EVENT!"
    }

    private val ioScope = CoroutineScope(Dispatchers.IO + Job())

    fun showNotification(context: Context, event: YearlyEvent) {
        ioScope.launch {
            createNotificationChannel()
            val builder = NotificationCompat.Builder(App.instance, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(notificationTitle)
                .setContentText("!!!!!!!!!!!!!!!!!!!!! some text !!!!!!!!!!!!!!!!!!!!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            val notificationManager = NotificationManagerCompat.from(App.instance)
            notificationManager.notify(NOTIFICATION_ID, builder)
            Thread.sleep(5000)
            notificationManager.cancel(NOTIFICATION_ID)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Events Notification Channel"
            val descriptionText = "Is a new person"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                App.instance.getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}