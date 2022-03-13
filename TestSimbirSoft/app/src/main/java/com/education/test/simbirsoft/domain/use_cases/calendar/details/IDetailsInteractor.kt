package com.education.test.simbirsoft.domain.use_cases.calendar.details

import com.education.test.simbirsoft.data.db.Event

interface IDetailsInteractor {
    suspend fun getEvent(eventId: Long): Event
}