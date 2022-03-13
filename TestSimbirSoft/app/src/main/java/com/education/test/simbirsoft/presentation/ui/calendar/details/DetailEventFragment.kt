package com.education.test.simbirsoft.presentation.ui.calendar.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.education.test.simbirsoft.presentation.view_models.calendar.details.DetailEventViewModel
import com.education.test_simbirsoft.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailEventFragment : Fragment() {

    private val viewModel: DetailEventViewModel by viewModels()

    private var eventId: Long? = null

    private var eventNameTv: AppCompatTextView? = null
    private var eventDescriptionTv: AppCompatTextView? = null
    private var eventTimeTv: AppCompatTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            eventId = getLong(EXTRA_EVENT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initObservers()
        eventId?.let { viewModel.getEvent(it) }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.event.collect {
                    eventNameTv?.text = it?.name
                    eventDescriptionTv?.text = it?.description
                    eventTimeTv?.text = "${it?.dateStart} - ${it?.dateFinish}"
                }
            }
        }
    }

    private fun initViews(view: View) {
        view.apply {
            eventNameTv = findViewById(R.id.fragment_details_event__name_tv)
            eventDescriptionTv = findViewById(R.id.fragment_details_event__description_tv)
            eventTimeTv = findViewById(R.id.fragment_details_event__time_tv)
        }
    }

    companion object {

        private const val EXTRA_EVENT_ID = "extra_event_id"

        @JvmStatic
        fun newInstance(eventId: Long) =
            DetailEventFragment().apply {
                arguments = Bundle().apply {
                    putLong(EXTRA_EVENT_ID, eventId)
                }
            }
    }
}
