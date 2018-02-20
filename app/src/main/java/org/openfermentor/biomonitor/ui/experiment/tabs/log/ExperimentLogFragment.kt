package org.openfermentor.biomonitor.ui.experiment.tabs.log

import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.hannesdorfmann.fragmentargs.bundler.ParcelerArgsBundler
import kotlinx.android.synthetic.main.fragment_experiment_log.*
import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.Experiment
import org.openfermentor.biomonitor.model.LogEntry
import org.openfermentor.biomonitor.ui.common.base.BioMonitorFragment
import org.openfermentor.biomonitor.ui.common.interfaces.Titleable
import javax.inject.Inject

@FragmentWithArgs
class ExperimentLogFragment : BioMonitorFragment<ExperimentLogView, ExperimentLogPresenter>(),
    ExperimentLogView, Titleable {
  @Arg(bundler = ParcelerArgsBundler::class)
  override lateinit var experiment: Experiment
  
  @Inject
  override lateinit var presenter: ExperimentLogPresenter
  
  override val layoutResId = R.layout.fragment_experiment_log
  override val title = App.context.getString(R.string.events)
  
  private val listAdapter by lazy { LogEntryAdapter() }
  
  override fun setup() {
    super.setup()
  
    progressBar.indeterminateTintList = ColorStateList.valueOf(Color.WHITE)
    recyclerView.apply {
      layoutManager = LinearLayoutManager(context)
      isNestedScrollingEnabled = false
      adapter = listAdapter
    }
  }
  
  override fun showMessage(message: String) {
    recyclerView.visibility = View.GONE
    progressBar.visibility = View.GONE
    
    messageText.text = message
    messageText.visibility = View.VISIBLE
  }
  
  override fun logEntriesLoaded(entries: List<LogEntry>) {
    progressBar.visibility = View.GONE
    recyclerView.visibility = View.VISIBLE
    listAdapter.setItems(entries)
  }
}
