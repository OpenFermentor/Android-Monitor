package org.openfermentor.biomonitor.ui.experiment.tabs.overview.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_experiment_overview_basic_info.view.*
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.helper.extensions.secondsToHourMinutes
import org.openfermentor.biomonitor.model.Experiment

class ExperimentOverviewBasicInfoView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  private val view = LayoutInflater.from(context).inflate(R.layout.view_experiment_overview_basic_info, this, true)
  
  fun setup(experiment: Experiment) {
    view.strainDescription.text = experiment.strain
    view.mediumDescription.text = experiment.medium
    view.estimatedTimeDescription.text = experiment.estimatedTimeSeconds.toLong().secondsToHourMinutes()
    
    view.notesTitle.visibility = if (experiment.extraNotes.isNullOrBlank()) View.GONE else View.VISIBLE
    view.notesDescription.visibility = if (experiment.extraNotes.isNullOrBlank()) View.GONE else View.VISIBLE
    experiment.extraNotes?.let { view.notesDescription.text = it }
  }
}
