package com.pahomovichk.remindMeDate

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

object Constants {
    // keys
    const val ID = "ID"
    const val NAME = "NAME"
    const val TYPE = "TYPE"
    const val DATE = "DATE"
    const val COMMENT = "COMMENT"

    const val SELECTION_ITEM = "SELECTION_ITEM"

    val VIEW_LOCAL_FORMATTER = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
    val GETTING_LOCAL_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    val SIMPLE_DATE_FORMATTER = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    val DEFAULT_DATE: LocalDate = LocalDate.of(2000, 1, 1)

    val EVENTS_WORKER_TAG = "EVENTS_WORKER_TAG"
}