package com.education.test.simbirsoft.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {
    @Query("SELECT * FROM EVENTS WHERE date_start > :startDate AND date_finish < :endDate ORDER BY date_start")
    suspend fun getEventListByDate(startDate: String, endDate: String): List<EventEntity>

    @Query("SELECT * FROM EVENTS WHERE id = :eventId")
    suspend fun getEvent(eventId: Long): EventEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(eventEntity: EventEntity)
}