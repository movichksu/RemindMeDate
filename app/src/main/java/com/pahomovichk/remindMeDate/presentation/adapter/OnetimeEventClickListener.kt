package com.pahomovichk.remindMeDate.presentation.adapter

import com.pahomovichk.remindMeDate.domain.entity.OnetimeEvent

interface OnetimeEventClickListener {
    fun onClick(event: OnetimeEvent)
}