package com.education.test.simbirsoft.data.repository

import com.education.test.simbirsoft.data.db.EventDao
import com.education.test.simbirsoft.data.db.EventEntity
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val eventDao: EventDao) :
    ICalendarRepository,
    ICreateRepository,
    IDetailsRepository {

    override suspend fun getListOfEventByDate(date: String): List<EventEntity> {
        val endDate = (date.toLong() + oneDay).toString()
        return eventDao.getEventListByDate(date, endDate)
    }

    override suspend fun createEvent(event: EventEntity) {
        eventDao.insertEvent(event)
    }

    override suspend fun getEvent(eventId: Long) : EventEntity {
        return eventDao.getEvent(eventId)
    }

    companion object {
        private const val oneDay = 86400L
    }
}
