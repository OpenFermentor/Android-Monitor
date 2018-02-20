package org.openfermentor.biomonitor.ui.common.adapters

import android.view.ViewGroup
import com.xmartlabs.bigbang.ui.common.recyclerview.SingleItemBaseRecyclerViewAdapter
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.Tag
import org.openfermentor.biomonitor.ui.common.views.TagItemView

class TagAdapter : SingleItemBaseRecyclerViewAdapter<Tag, TagItemView>() {
  override fun onCreateViewHolder(parent: ViewGroup) =
      TagItemView(inflateView(parent, R.layout.view_tag_item), null)
}
