package org.openfermentor.biomonitor.ui.experiment.tabs.overview.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_experiment_overview_temperature.view.*
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.Experiment
import java.util.Locale

class ExperimentOverviewTemperatureView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
  private val view = LayoutInflater.from(context).inflate(R.layout.view_experiment_overview_temperature, this, true)
  
  fun setup(experiment: Experiment) {
    view.temperatureTarget.text = String.format(Locale.getDefault(),
        context.getString(R.string.temperature_value_with_unit), experiment.targetTemp)
    view.temperatureTolerance.text = experiment.tempTolerance.toString()
  }
}
