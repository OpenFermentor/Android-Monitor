package org.openfermentor.biomonitor.ui.experiment.tabs.report

import android.view.ViewGroup
import com.xmartlabs.bigbang.ui.common.recyclerview.SingleItemBaseRecyclerViewAdapter
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.ui.experiment.tabs.report.model.ReportItem
import org.openfermentor.biomonitor.ui.experiment.tabs.report.views.ReportItemView

class ExperimentReportAdapter : SingleItemBaseRecyclerViewAdapter<ReportItem, ReportItemView>() {
  override fun onCreateViewHolder(parent: ViewGroup) =
      ReportItemView(inflateView(parent, R.layout.view_report_item))
}
