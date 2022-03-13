package com.education.test.simbirsoft.domain.use_cases.calendar.create

interface ICreateInteractor {
    suspend fun createEvent(date: String, time: String, name: String, description: String)
}