package com.pahomovichk.remindMeDate.presentation.adapter

import com.pahomovichk.remindMeDate.domain.entity.YearlyEvent

interface YearlyEventClickListener {
    fun onClick(event: YearlyEvent)
}