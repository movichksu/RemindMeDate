package com.pahomovichk.remindMeDate.presentation.adapter

import com.pahomovichk.remindMeDate.domain.entity.Birthday

interface BirthdayClickListener {
    fun onClick(birthday: Birthday)
}