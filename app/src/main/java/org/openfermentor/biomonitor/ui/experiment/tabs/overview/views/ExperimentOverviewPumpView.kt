package org.openfermentor.biomonitor.ui.experiment.tabs.overview.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_experiment_overview_pump.view.*
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.helper.extensions.secondsToHourMinutes
import org.openfermentor.biomonitor.model.Experiment

/**
 * Created by diegomedina on 11/7/17.
 */
class ExperimentOverviewPumpView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  private val view = LayoutInflater.from(context).inflate(R.layout.view_experiment_overview_pump, this, true)
  
  fun setup(experiment: Experiment) {
    val showPumpInfo = experiment.triggerAfter != null || experiment.triggerFor != null
    view.pumpContainer.visibility = if (showPumpInfo) View.VISIBLE else View.GONE
    view.pumpTitle.visibility = if (showPumpInfo) View.VISIBLE else View.GONE
    
    view.pumpStartAt.text = experiment.triggerAfter?.secondsToHourMinutes() ?: "-"
    view.pumpFor.text = experiment.triggerFor?.secondsToHourMinutes() ?: "-"
  }
}
