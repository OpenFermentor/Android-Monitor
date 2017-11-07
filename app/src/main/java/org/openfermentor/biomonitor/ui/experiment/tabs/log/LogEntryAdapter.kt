package org.openfermentor.biomonitor.ui.experiment.tabs.log

import android.view.ViewGroup
import com.xmartlabs.bigbang.ui.common.recyclerview.SingleItemBaseRecyclerViewAdapter
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.LogEntry
import org.openfermentor.biomonitor.ui.experiment.tabs.log.views.LogEntryItemView

class LogEntryAdapter : SingleItemBaseRecyclerViewAdapter<LogEntry, LogEntryItemView>() {
  override fun onCreateViewHolder(parent: ViewGroup) =
      LogEntryItemView(inflateView(parent, R.layout.view_log_entry_item))
}
