package com.pahomovichk.remindMeDate.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.pahomovichk.remindMeDate.presentation.worker.YearlyWorker
import java.util.concurrent.TimeUnit


class WorkerUseCaseImpl(
    private val workManager: WorkManager
): WorkerUseCase {
    override fun yearlyNotificationRequest() {
        val yearlyRequest = PeriodicWorkRequestBuilder<YearlyWorker>(
            12, TimeUnit.HOURS, // repeatInterval (the period cycle)
            15, TimeUnit.MINUTES) // flexInterval
            .addTag("yearly")
            .build()
        workManager.enqueue(yearlyRequest)
    }
}