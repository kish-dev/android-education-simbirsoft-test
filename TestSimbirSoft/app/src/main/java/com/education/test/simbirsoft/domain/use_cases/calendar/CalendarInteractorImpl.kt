package com.education.test.simbirsoft.domain.use_cases.calendar

import com.education.test.simbirsoft.data.db.Event
import com.education.test.simbirsoft.data.db.mapToDomain
import com.education.test.simbirsoft.data.repository.ICalendarRepository
import javax.inject.Inject

class CalendarInteractorImpl @Inject constructor(
    private val calendarRepository: ICalendarRepository
) : ICalendarInteractor {
    override suspend fun getListOfEventByDate(date: String): List<Event> {
        return calendarRepository.getListOfEventByDate(date).map { it.mapToDomain() }
    }

}