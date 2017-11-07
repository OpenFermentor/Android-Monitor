package org.openfermentor.biomonitor.ui.main.tabs.system

import android.support.v7.widget.LinearLayoutManager
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import kotlinx.android.synthetic.main.fragment_system_status.*
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.ui.common.base.BioMonitorFragment
import org.openfermentor.biomonitor.ui.main.tabs.system.model.SystemStatus
import org.openfermentor.biomonitor.ui.main.tabs.system.model.SystemVariable
import javax.inject.Inject

@FragmentWithArgs
class SystemStatusFragment : BioMonitorFragment<SystemStatusView, SystemStatusPresenter>(), SystemStatusView {
  @Inject
  override lateinit var presenter: SystemStatusPresenter
  
  override val layoutResId = R.layout.fragment_system_status
  
  private val adapter by lazy { SystemStatusAdapter() }
  
  override fun setup() {
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.isNestedScrollingEnabled = false
    recyclerView.adapter = adapter
  }
  
  override fun loadVariables(variables: List<SystemVariable>) {
    adapter.setItems(variables)
  }
  
  override fun changeStatus(status: SystemStatus) {
    statusTitle.text = status.title ?: ""
    statusDescription.text = status.description ?: ""
  }
}
