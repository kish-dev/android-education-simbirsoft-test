package com.education.test.simbirsoft.domain.use_cases.calendar.details

import com.education.test.simbirsoft.data.db.Event
import com.education.test.simbirsoft.data.db.mapToDomain
import com.education.test.simbirsoft.data.repository.IDetailsRepository
import javax.inject.Inject

class DetailsInteractorImpl @Inject constructor(private val detailsRepository: IDetailsRepository): IDetailsInteractor {
    override suspend fun getEvent(eventId: Long): Event {
        return detailsRepository.getEvent(eventId).mapToDomain()
    }
}