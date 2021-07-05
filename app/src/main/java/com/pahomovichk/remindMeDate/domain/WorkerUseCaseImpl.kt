package com.pahomovichk.remindMeDate.domain

import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.pahomovichk.remindMeDate.Constants
import com.pahomovichk.remindMeDate.presentation.worker.YearlyWorker
import java.util.concurrent.TimeUnit


class WorkerUseCaseImpl(
    private val workManager: WorkManager
): WorkerUseCase {
    override fun yearlyNotificationRequest() {
        val yearlyRequest = PeriodicWorkRequestBuilder<YearlyWorker>(
            15, TimeUnit.MINUTES, // repeatInterval (the period cycle)
            10, TimeUnit.MINUTES) // flexInterval
            .addTag(Constants.EVENTS_WORKER_TAG)
            .build()
        workManager.enqueue(yearlyRequest)
    }
}