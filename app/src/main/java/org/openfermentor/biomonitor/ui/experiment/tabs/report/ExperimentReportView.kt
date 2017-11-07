package org.openfermentor.biomonitor.ui.experiment.tabs.report

import org.openfermentor.biomonitor.ui.common.base.BioMonitorView
import org.openfermentor.biomonitor.ui.experiment.tabs.report.model.ReportItem

/**
 * Created by diegomedina on 11/5/17.
 */
interface ExperimentReportView : BioMonitorView {
  fun reportDataLoaded(reports: List<ReportItem>)
}
