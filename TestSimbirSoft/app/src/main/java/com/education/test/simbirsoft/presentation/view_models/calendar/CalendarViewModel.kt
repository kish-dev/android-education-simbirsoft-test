package com.education.test.simbirsoft.presentation.view_models.calendar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.education.test.simbirsoft.data.db.Event
import com.education.test.simbirsoft.domain.use_cases.calendar.ICalendarInteractor
import com.education.test.simbirsoft.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val interactor: ICalendarInteractor,
) :
    ViewModel() {

    private val _eventList = MutableStateFlow<List<Event>>(emptyList())
    val eventList: StateFlow<List<Event>> = _eventList

    fun getEventListByDate(date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val eventListResult = interactor.getListOfEventByDate(date)
            withContext(Dispatchers.Main) {
                _eventList.value = eventListResult
            }
        }
    }

    fun initFirstState() {
        getEventListByDate(Utils().getCurrentDate())
    }
}
