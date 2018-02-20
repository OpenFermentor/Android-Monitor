package org.openfermentor.biomonitor.ui.main.tabs.experiments

import org.openfermentor.biomonitor.model.Experiment
import org.openfermentor.biomonitor.ui.common.base.BioMonitorView

interface ExperimentListView : BioMonitorView {
  fun experimentsLoaded(experiments: List<Experiment>)
  fun goToExperimentActivity(experiment: Experiment)
  fun clearSearchBar()
  fun closeSearchBar()
  fun setProgressBarVisible(visible: Boolean)
  fun showErrorMessage(error: String)
}
