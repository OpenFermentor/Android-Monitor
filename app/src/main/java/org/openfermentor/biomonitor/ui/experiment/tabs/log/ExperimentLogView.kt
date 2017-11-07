package org.openfermentor.biomonitor.ui.experiment.tabs.log

import org.openfermentor.biomonitor.model.Experiment
import org.openfermentor.biomonitor.model.LogEntry
import org.openfermentor.biomonitor.ui.common.base.BioMonitorView

interface ExperimentLogView : BioMonitorView {
  var experiment: Experiment
  
  fun showMessage(message: String)
  fun logEntriesLoaded(entries: List<LogEntry>)
}
