package com.education.test.simbirsoft.data.repository

import com.education.test.simbirsoft.data.db.EventEntity

interface ICalendarRepository {
    suspend fun getListOfEventByDate(date: String): List<EventEntity>

}