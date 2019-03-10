package com.example.pewpew.exrate.features.exchangerates.utils

import java.text.SimpleDateFormat
import java.util.*

object CalendarHelper {
    val today: Date = Calendar.getInstance().time
    val tomorrow: Date = Calendar.getInstance().apply { add(Calendar.DATE, 1) }.time
    val yesterday: Date = Calendar.getInstance().apply { add(Calendar.DATE, -1) }.time

    var firstDate: Date? = null
    var secondDate: Date? = null
}

fun Date.toApiDate(): String = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(this)

fun Date.toViewDate(): String = SimpleDateFormat("dd.MM.yy", Locale.US).format(this)