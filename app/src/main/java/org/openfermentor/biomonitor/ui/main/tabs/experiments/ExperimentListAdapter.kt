package org.openfermentor.biomonitor.ui.main.tabs.experiments

import android.view.ViewGroup
import com.xmartlabs.bigbang.ui.common.recyclerview.SingleItemBaseRecyclerViewAdapter
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.Experiment
import org.openfermentor.biomonitor.ui.main.tabs.experiments.views.ExperimentListItemView

class ExperimentListAdapter : SingleItemBaseRecyclerViewAdapter<Experiment, ExperimentListItemView>() {
  var onClickListener: ((Experiment) -> Unit)? = null
  
  override fun onCreateViewHolder(parent: ViewGroup) =
      ExperimentListItemView(inflateView(parent, R.layout.view_experiment_list_item), onClickListener)
}
