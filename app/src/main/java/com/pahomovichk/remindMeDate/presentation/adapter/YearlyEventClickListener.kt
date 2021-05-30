package com.pahomovichk.remindMeDate.presentation.adapter

import com.pahomovichk.remindMeDate.domain.entity.Birthday

interface YearlyEventClickListener {
    fun onClick(birthday: Birthday)
}