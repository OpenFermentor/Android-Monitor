package org.openfermentor.biomonitor.ui.main.tabs.system

import org.openfermentor.biomonitor.ui.common.base.BioMonitorView
import org.openfermentor.biomonitor.ui.main.tabs.system.model.SystemStatus
import org.openfermentor.biomonitor.ui.main.tabs.system.model.SystemVariable

interface SystemStatusView : BioMonitorView {
  fun loadVariables(variables: List<SystemVariable>)
  fun changeStatus(status: SystemStatus)
}
