package org.openfermentor.biomonitor.ui.common.views

import android.view.View
import com.xmartlabs.bigbang.ui.common.recyclerview.SingleItemBaseViewHolder
import kotlinx.android.synthetic.main.view_tag_item.view.*
import org.openfermentor.biomonitor.model.Tag

class TagItemView(
    view: View,
    onClickListener: ((Tag) -> Unit)? = null
) : SingleItemBaseViewHolder<Tag>(view, onClickListener) {
  override fun bindItem(item: Tag) {
    super.bindItem(item)
    
    itemView.tagText.text = item.value
  }
}
