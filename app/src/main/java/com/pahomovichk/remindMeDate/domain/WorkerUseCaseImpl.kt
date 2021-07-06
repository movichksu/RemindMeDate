package com.pahomovichk.remindMeDate.domain

import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.pahomovichk.remindMeDate.Constants
import com.pahomovichk.remindMeDate.presentation.worker.OnetimeWorker
import com.pahomovichk.remindMeDate.presentation.worker.YearlyWorker
import java.util.concurrent.TimeUnit


class WorkerUseCaseImpl(
    private val workManager: WorkManager
): WorkerUseCase {
    override fun yearlyNotificationRequest() {
        val yearlyRequest = PeriodicWorkRequestBuilder<YearlyWorker>(
            12, TimeUnit.HOURS, // repeatInterval (the period cycle)
            10, TimeUnit.HOURS) // flexInterval
            .addTag(Constants.YEARLY_EVENTS_WORKER_TAG)
            .build()
        workManager.enqueue(yearlyRequest)
    }

    override fun onetimeNotificationRequest() {
        val onetimeRequest = PeriodicWorkRequestBuilder<OnetimeWorker>(
            12, TimeUnit.HOURS, // repeatInterval (the period cycle)
            10, TimeUnit.HOURS) // flexInterval
            .addTag(Constants.YEARLY_EVENTS_WORKER_TAG)
            .build()
        workManager.enqueue(onetimeRequest)
    }
}