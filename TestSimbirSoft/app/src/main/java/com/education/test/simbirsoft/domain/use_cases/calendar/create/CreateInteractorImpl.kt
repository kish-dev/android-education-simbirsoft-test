package com.education.test.simbirsoft.domain.use_cases.calendar.create

import com.education.test.simbirsoft.data.db.EventEntity
import com.education.test.simbirsoft.data.repository.ICreateRepository
import com.education.test.simbirsoft.utils.Utils
import com.education.test.simbirsoft.utils.convertToStartHourInUnix
import javax.inject.Inject

class CreateInteractorImpl @Inject constructor(
    private val createRepository: ICreateRepository
) : ICreateInteractor {

    override suspend fun createEvent(
        date: String,
        time: String,
        name: String,
        description: String
    ) {
        val dateStart = time.convertToStartHourInUnix(date)
        val dateFinish = (dateStart.toLong() + Utils.ONE_HOUR).toString()
        createRepository.createEvent(
            EventEntity(
                id = null,
                dateStart = dateStart,
                dateFinish = dateFinish,
                name = name,
                description = description
            )
        )
    }

}