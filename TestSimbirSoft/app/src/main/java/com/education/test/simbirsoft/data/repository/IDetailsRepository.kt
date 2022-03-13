package com.education.test.simbirsoft.data.repository

import com.education.test.simbirsoft.data.db.EventEntity

interface IDetailsRepository {
    suspend fun getEvent(eventId: Long): EventEntity
}