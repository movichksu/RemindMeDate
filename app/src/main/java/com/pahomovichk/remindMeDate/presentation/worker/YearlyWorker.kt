package com.pahomovichk.remindMeDate.presentation.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.cleanarchitechture.App
import com.pahomovichk.remindMeDate.Dependencies
import com.pahomovichk.remindMeDate.domain.YearlyEventsUseCase
import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent
import com.pahomovichk.remindMeDate.presentation.notification.YearlyNotification
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import java.security.PrivateKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class YearlyWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val yearlyUseCase: YearlyEventsUseCase = Dependencies.getYearlyEventUseCase()
    private val ioScope = CoroutineScope(Dispatchers.IO + Job())

    private lateinit var now: LocalDate
    private lateinit var events: List<YearlyEvent>

    override suspend fun doWork(): Result {
        try {
                events = yearlyUseCase.getAllEvents()
                    Log.d("DO_WORK", "worker")
                    val notification = YearlyNotification()
                    now = LocalDate.now()
                    for (i in 0..events.size) {
                        if (now == events.get(i).date) {
                            Log.d("DO_WORK", "${now.format(DateTimeFormatter.ISO_DATE)} == ${events.get(i).date}")
                            notification.showNotification(App.instance, events.get(i))
                        }
                    }
                    return Result.success()
        }
        catch (e: Exception){
            return Result.failure()
        }
    }
}