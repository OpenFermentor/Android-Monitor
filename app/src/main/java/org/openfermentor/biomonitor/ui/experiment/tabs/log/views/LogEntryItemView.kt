package org.openfermentor.biomonitor.ui.experiment.tabs.log.views

import android.view.View
import com.xmartlabs.bigbang.ui.common.recyclerview.SingleItemBaseViewHolder
import kotlinx.android.synthetic.main.view_log_entry_item.view.*
import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.LogEntry
import org.threeten.bp.format.DateTimeFormatter

class LogEntryItemView(
    view: View,
    onClickListener: ((LogEntry) -> Unit)? = null
) : SingleItemBaseViewHolder<LogEntry>(view, onClickListener) {
  companion object {
    private val DATE_FORMATTER = DateTimeFormatter.ofPattern(App.context.getString(R.string.log_entry_date))
  }
  
  override fun bindItem(item: LogEntry) {
    super.bindItem(item)
    
    itemView.itemContainer.setBackgroundColor(item.type.color)
    itemView.itemTitle.text = item.type.title
    itemView.itemDate.text = DATE_FORMATTER.format(item.date)
    itemView.itemDescription.text = item.description
  }
}
