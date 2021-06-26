package com.pahomovichk.remindMeDate

import androidx.work.WorkManager
import com.example.cleanarchitechture.App
import com.pahomovichk.remindMeDate.data.database.LocalDatabaseSource
import com.pahomovichk.remindMeDate.domain.*

object Dependencies {
    private val yearlyEventsRepository: YearlyEventsRepository by lazy {LocalDatabaseSource(App.instance)}
    private val onetimeEventsRepository: OnetimeEventsRepository by lazy{LocalDatabaseSource(App.instance)}

    private fun getYearlyEventRepository(): YearlyEventsRepository{
        return yearlyEventsRepository
    }
    private fun getOnetimeEventRepository(): OnetimeEventsRepository{
        return onetimeEventsRepository
    }

    fun getYearlyEventUseCase(): YearlyEventsUseCase{
        return YearlyEventsUseCaseImpl(getYearlyEventRepository())
    }
    fun getOnetimeEventUseCase(): OnetimeEventsUseCase{
        return OnetimeEventsUseCaseImpl(getOnetimeEventRepository())
    }

    fun getWorkerUseCase(): WorkerUseCase{
        return WorkerUseCaseImpl(WorkManager.getInstance(App.instance))
    }
}