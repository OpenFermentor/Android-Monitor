package org.openfermentor.biomonitor.ui.main.tabs.system.model

import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R

enum class SystemStatus(
    titleRes: Int? = null,
    descriptionRes: Int? = null
) {
  NO_CONNECTION(R.string.no_connection, R.string.no_connection_description),
  NO_RUNNING_EXPERIMENT(R.string.no_running_experiment, R.string.no_running_experiment_description),
  RUNNING_EXPERIMENT(R.string.experiment_running, R.string.experiment_running_description),
  ;
  
  val title = titleRes?.let { App.context.getString(it) }
  val description = descriptionRes?.let { App.context.getString(it) }
}
