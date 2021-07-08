package com.pahomovichk.remindMeDate.presentation.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.cleanarchitechture.App
import com.pahomovichk.remindMeDate.Dependencies
import com.pahomovichk.remindMeDate.domain.OnetimeEventsUseCase
import com.pahomovichk.remindMeDate.domain.YearlyEventsUseCase
import com.pahomovichk.remindMeDate.domain.entity.OnetimeEvent
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import com.pahomovichk.remindMeDate.presentation.notification.YearlyNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class OnetimeWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val onetimeUseCase: OnetimeEventsUseCase = Dependencies.getOnetimeEventUseCase()

    private lateinit var now: LocalDate
    private lateinit var events: List<OnetimeEvent>

    override suspend fun doWork(): Result {
        try {
            events = onetimeUseCase.getAllEvents()
            val notification = YearlyNotification()
            now = LocalDate.now()
            for (i in 0..events.size) {
                if (now == events.get(i).date) {
                    notification.showNotification(App.instance, events.get(i))
                }
            }
            return Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }
}