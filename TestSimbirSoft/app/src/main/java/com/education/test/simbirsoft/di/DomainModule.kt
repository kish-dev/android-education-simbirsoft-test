package com.education.test.simbirsoft.di

import com.education.test.simbirsoft.data.db.EventDao
import com.education.test.simbirsoft.data.repository.ICalendarRepository
import com.education.test.simbirsoft.data.repository.ICreateRepository
import com.education.test.simbirsoft.data.repository.IDetailsRepository
import com.education.test.simbirsoft.data.repository.LocalRepositoryImpl
import com.education.test.simbirsoft.domain.use_cases.calendar.CalendarInteractorImpl
import com.education.test.simbirsoft.domain.use_cases.calendar.ICalendarInteractor
import com.education.test.simbirsoft.domain.use_cases.calendar.create.CreateInteractorImpl
import com.education.test.simbirsoft.domain.use_cases.calendar.create.ICreateInteractor
import com.education.test.simbirsoft.domain.use_cases.calendar.details.DetailsInteractorImpl
import com.education.test.simbirsoft.domain.use_cases.calendar.details.IDetailsInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideCalendarInteractor(
        calendarRepository: ICalendarRepository
    ): ICalendarInteractor {
        return CalendarInteractorImpl(calendarRepository = calendarRepository)
    }

    @Singleton
    @Provides
    fun provideCreateInteractor(
        createRepository: ICreateRepository
    ): ICreateInteractor {
        return CreateInteractorImpl(createRepository = createRepository)
    }

    @Singleton
    @Provides
    fun provideDetailsInteractor(
        detailsRepository: IDetailsRepository
    ): IDetailsInteractor {
        return DetailsInteractorImpl(detailsRepository = detailsRepository)
    }

    @Singleton
    @Provides
    fun provideLocalCalendarRepository(eventDao: EventDao): ICalendarRepository {
        return LocalRepositoryImpl(eventDao)
    }

    @Singleton
    @Provides
    fun provideLocalCreateRepository(eventDao: EventDao): ICreateRepository {
        return LocalRepositoryImpl(eventDao)
    }

    @Singleton
    @Provides
    fun provideLocalDetailsRepository(eventDao: EventDao): IDetailsRepository {
        return LocalRepositoryImpl(eventDao)
    }

}