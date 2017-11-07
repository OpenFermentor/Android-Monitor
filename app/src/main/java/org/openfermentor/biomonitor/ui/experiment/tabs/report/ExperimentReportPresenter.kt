package org.openfermentor.biomonitor.ui.experiment.tabs.report

import com.github.mikephil.charting.data.Entry
import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.ui.common.base.BioMonitorPresenter
import org.openfermentor.biomonitor.ui.experiment.tabs.report.model.ReportItem
import javax.inject.Inject

class ExperimentReportPresenter @Inject constructor() : BioMonitorPresenter<ExperimentReportView>() {
  override fun attachView(view: ExperimentReportView) {
    super.attachView(view)
    
    val reports = listOf(
        ReportItem().apply {
          title = App.context.getString(R.string.temperature)
          data = (401..408).mapIndexed { index, value -> Entry(index * 10f, value / 10f) }
        },
        ReportItem().apply {
          title = App.context.getString(R.string.ph)
          data = (71..76).mapIndexed { index, value -> Entry(index * 10f, value / 10f) }
        }
    )
    
    view.reportDataLoaded(reports)
  }
}
