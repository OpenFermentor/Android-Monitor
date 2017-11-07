package org.openfermentor.biomonitor.ui.main.tabs.system.views

import android.support.v4.content.ContextCompat
import android.view.View
import com.xmartlabs.bigbang.ui.common.recyclerview.SingleItemBaseViewHolder
import kotlinx.android.synthetic.main.view_system_variable.view.*
import org.openfermentor.biomonitor.ui.main.tabs.system.model.SystemVariable

class SystemVariableView(
    view: View,
    onClickListener: ((SystemVariable) -> Unit)? = null
) : SingleItemBaseViewHolder<SystemVariable>(view, onClickListener) {
  override fun bindItem(item: SystemVariable) {
    super.bindItem(item)
    
    itemView.variableIcon.setImageDrawable(ContextCompat.getDrawable(context, item.icon))
    itemView.variableValue.text = item.text
    itemView.variableUnit.text = item.unit ?: ""
    
    itemView.variableStatus.text = item.status.text
    itemView.variableStatus.setTextColor(item.status.textColor)
  }
}
