package org.openfermentor.biomonitor.ui.main.tabs.experiments.views

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.android.flexbox.AlignItems
import com.xmartlabs.bigbang.ui.common.recyclerview.SingleItemBaseViewHolder
import kotlinx.android.synthetic.main.view_experiment_list_item.view.*
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.Experiment
import java.util.Locale
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import org.openfermentor.biomonitor.ui.common.adapters.TagAdapter

class ExperimentListItemView(
    view: View,
    onClickListener: ((Experiment) -> Unit)? = null
) : SingleItemBaseViewHolder<Experiment>(view, onClickListener) {
  override fun bindItem(item: Experiment) {
    super.bindItem(item)
    
    itemView.statusTitle.text = item.title
    itemView.temperatureValue.text = String.format(Locale.getDefault(),
        context.getString(R.string.temperature_value_with_unit), item.targetTemp)
    itemView.phValue.text = item.targetPh.toString()
    
    setupTagsView(item)
  }
  
  private fun setupTagsView(item: Experiment) {
    itemView.tagsContainer.visibility = if (item.tags?.isEmpty() == true) View.GONE else View.VISIBLE
    
    val layoutManager = LinearLayoutManager(context)
    layoutManager.orientation = LinearLayoutManager.HORIZONTAL

    itemView.tagsRecyclerView.layoutManager = layoutManager
    itemView.tagsRecyclerView.isNestedScrollingEnabled = false
    
    val adapter = TagAdapter()
    itemView.tagsRecyclerView.adapter = adapter
    adapter.setItems(item.tags ?: emptyList())
  }
}
