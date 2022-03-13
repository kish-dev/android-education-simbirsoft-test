package com.education.test.simbirsoft.presentation.ui.calendar.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.applandeo.materialcalendarview.CalendarView
import com.education.test.simbirsoft.presentation.view_models.calendar.create.CreateEventViewModel
import com.education.test.simbirsoft.utils.Utils
import android.widget.AdapterView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.education.test_simbirsoft.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateEventFragment : Fragment() {

    private val viewModel: CreateEventViewModel by viewModels()

    private var calendarView: CalendarView? = null
    private var timeSpinner: AppCompatSpinner? = null
    private var nameEt: AppCompatEditText? = null
    private var descriptionEt: AppCompatEditText? = null
    private var createButton: AppCompatButton? = null

    private var date: String = Utils().getCurrentDate()
    private var time: String = "00:00 - 01:00"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initObservers()
        setOnClickListeners()
    }

    private fun initViews(view: View) {
        view.apply {
            calendarView = findViewById(R.id.fragment_create_event__calendar_view)
            timeSpinner = findViewById(R.id.fragment_create_event__time_spinner)
            nameEt = findViewById(R.id.fragment_create_event__event_name_et)
            descriptionEt = findViewById(R.id.fragment_create_event__event_description_et)
            createButton = findViewById(R.id.fragment_create_event__create_event_button)
        }

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.time,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner?.adapter = adapter
    }

    private fun initObservers() {

    }

    private fun setOnClickListeners() {

        timeSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                time = resources.getStringArray(R.array.time)[selectedItemPosition]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        calendarView?.setOnDayClickListener { eventDate ->
            date = (eventDate.calendar.timeInMillis / 1000).toString()
        }

        createButton?.setOnClickListener {
            if (isValidEvent(nameEt?.text.toString())) {
                viewModel.createEvent(
                    date = date,
                    time = time,
                    name = nameEt?.text.toString(),
                    description = descriptionEt?.text.toString()
                )
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    private fun isValidEvent(name: String): Boolean {
        return if (name.isNotEmpty()) {
            true
        } else {
            Toast.makeText(
                requireContext(),
                requireContext().getString(R.string.warning_add_name),
                Toast.LENGTH_SHORT
            ).show()
            false
        }
    }

    companion object {

    }
}
