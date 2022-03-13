package com.education.test.simbirsoft.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [EventEntity::class], version = 1, exportSchema = false)
@TypeConverters(JSONConverterEvent::class)
abstract class CalendarDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDao
}