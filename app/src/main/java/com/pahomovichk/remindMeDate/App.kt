package com.example.cleanarchitechture

import android.app.Application
import com.pahomovichk.remindMeDate.Dependencies
import com.pahomovichk.remindMeDate.domain.WorkerUseCase

class App : Application() {

    private val workerUseCase: WorkerUseCase by lazy { Dependencies.getWorkerUseCase() }

    override fun onCreate() {
        super.onCreate()
        instance = this

        //workerUseCase.yearlyNotificationRequest()
    }

    companion object {
        lateinit var instance: App
        private set
    }
}
