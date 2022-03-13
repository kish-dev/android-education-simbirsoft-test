package com.education.test.simbirsoft.di

import android.content.Context
import androidx.room.Room
import com.education.test.simbirsoft.data.db.CalendarDatabase
import com.education.test.simbirsoft.data.db.EventDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideCalendarDatabase(@ApplicationContext context: Context): CalendarDatabase {
        return Room.databaseBuilder(
            context,
            CalendarDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    fun provideEntityDao(calendarDatabase: CalendarDatabase): EventDao {
        return calendarDatabase.eventDao()
    }

    private companion object {
        const val DB_NAME = "cache_db"
    }
}