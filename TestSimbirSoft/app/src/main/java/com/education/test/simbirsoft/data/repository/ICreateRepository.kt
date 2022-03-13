package com.education.test.simbirsoft.data.repository

import com.education.test.simbirsoft.data.db.EventEntity

interface ICreateRepository {
    suspend fun createEvent(event: EventEntity)
}