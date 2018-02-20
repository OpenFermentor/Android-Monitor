package org.openfermentor.biomonitor.ui.main.tabs.system

import android.view.ViewGroup
import com.xmartlabs.bigbang.ui.common.recyclerview.SingleItemBaseRecyclerViewAdapter
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.ui.main.tabs.system.model.SystemVariable
import org.openfermentor.biomonitor.ui.main.tabs.system.views.SystemVariableView

class SystemStatusAdapter : SingleItemBaseRecyclerViewAdapter<SystemVariable, SystemVariableView>() {
  override fun onCreateViewHolder(parent: ViewGroup) =
      SystemVariableView(inflateView(parent, R.layout.view_system_variable), null)
}
