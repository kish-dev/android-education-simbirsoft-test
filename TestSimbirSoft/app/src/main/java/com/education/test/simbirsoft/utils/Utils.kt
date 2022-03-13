package com.education.test.simbirsoft.utils

class Utils {
    fun getCurrentDate(): String {
        val currentTime = System.currentTimeMillis() / 1000
        return (currentTime - (currentTime - currentTime / 86400L * 86400L)).toString()
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