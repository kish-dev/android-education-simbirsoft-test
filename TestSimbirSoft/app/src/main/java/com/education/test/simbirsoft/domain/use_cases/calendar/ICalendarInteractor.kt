package com.education.test.simbirsoft.domain.use_cases.calendar

import com.education.test.simbirsoft.data.db.Event

interface ICalendarInteractor {
    suspend fun getListOfEventByDate(date: String): List<Event>
}