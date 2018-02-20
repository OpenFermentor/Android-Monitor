package org.openfermentor.biomonitor.ui.experiment.tabs.overview.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_experiment_overview_ph.view.*
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.Experiment

class ExperimentOverviewPhView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  private val view = LayoutInflater.from(context).inflate(R.layout.view_experiment_overview_ph, this, true)
  
  fun setup(experiment: Experiment) {
    view.phTarget.text = experiment.targetPh.toString()
    view.phTolerance.text = experiment.phTolerance.toString()
  }
}
