package com.pahomovichk.remindMeDate.presentation.adapter

import com.pahomovichk.remindMeDate.domain.entity.Event

interface EventClickListener {
    fun onClick(event: Event)
}