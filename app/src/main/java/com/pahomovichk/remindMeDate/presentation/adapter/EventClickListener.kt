package com.pahomovichk.remindMeDate.presentation.adapter

import com.pahomovichk.remindMeDate.entity.Birthday
import com.pahomovichk.remindMeDate.entity.Event

interface EventClickListener {
    fun onClick(event: Event)
}