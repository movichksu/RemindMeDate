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

    val viewLocalFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
    val gettingLocalFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    val simpleDateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    val defaultDate: LocalDate = LocalDate.of(2000, 1, 1)
}