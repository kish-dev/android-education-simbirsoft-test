package com.education.test.simbirsoft.utils

import android.util.Log
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class Utils {
    fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateStart = LocalDateTime.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH),
            0,
            0,
            0,
        )

        return (dateStart.atZone(ZoneId.systemDefault()).toInstant()
            .toEpochMilli() / 1000L).toString()
    }

    companion object {
        const val ONE_DAY = 86400L
        const val ONE_HOUR = 3600L
    }

}

fun String.convertToStartHourInUnix(date: String): String {
    var hour = this.split(":")[0]
    if (hour[0] == '0') {
        hour = hour[1].toString()
    }
    return (date.toLong() + hour.toLong() * Utils.ONE_HOUR).toString()
}