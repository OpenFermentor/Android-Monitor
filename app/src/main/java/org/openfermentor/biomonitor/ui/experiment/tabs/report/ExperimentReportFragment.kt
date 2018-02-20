package org.openfermentor.biomonitor.ui.experiment.tabs.report

import android.support.v7.widget.LinearLayoutManager
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.hannesdorfmann.fragmentargs.bundler.ParcelerArgsBundler
import kotlinx.android.synthetic.main.fragment_system_status.*
import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.Experiment
import org.openfermentor.biomonitor.ui.common.base.BioMonitorFragment
import org.openfermentor.biomonitor.ui.common.interfaces.Titleable
import org.openfermentor.biomonitor.ui.experiment.tabs.log.LogEntryAdapter
import org.openfermentor.biomonitor.ui.experiment.tabs.report.model.ReportItem
import javax.inject.Inject

@FragmentWithArgs
class ExperimentReportFragment : BioMonitorFragment<ExperimentReportView, ExperimentReportPresenter>(),
    ExperimentReportView, Titleable {
  @Arg(bundler = ParcelerArgsBundler::class)
  lateinit var experiment: Experiment
  
  @Inject
  override lateinit var presenter: ExperimentReportPresenter
  
  override val layoutResId = R.layout.fragment_experiment_report
  override val title = App.context.getString(R.string.report)
  
  private val adapter by lazy { ExperimentReportAdapter() }
  
  override fun setup() {
    super.setup()
    
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.isNestedScrollingEnabled = false
    recyclerView.adapter = adapter
  }
  
  override fun reportDataLoaded(reports: List<ReportItem>) {
    adapter.setItems(reports)
  }
}
