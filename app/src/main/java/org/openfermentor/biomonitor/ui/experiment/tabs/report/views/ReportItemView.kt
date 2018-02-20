package org.openfermentor.biomonitor.ui.experiment.tabs.report.views

import android.support.v4.content.ContextCompat
import android.view.View
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.xmartlabs.bigbang.ui.common.recyclerview.SingleItemBaseViewHolder
import kotlinx.android.synthetic.main.view_report_item.view.*
import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.ui.experiment.tabs.report.model.ReportItem

class ReportItemView(
    view: View,
    onClickListener: ((ReportItem) -> Unit)? = null
) : SingleItemBaseViewHolder<ReportItem>(view, onClickListener) {
  companion object {
    private val DRAWABLE_BACKGROUND = ContextCompat.getDrawable(App.context, R.drawable.shape_graph_faded)
  }
  
  override fun bindItem(item: ReportItem) {
    super.bindItem(item)
    
    val dataSet = LineDataSet(item.data, "")
    dataSet.color = ContextCompat.getColor(context, R.color.light_green)
    dataSet.setCircleColor(ContextCompat.getColor(context, R.color.light_green_two))
    dataSet.lineWidth = 3f
    dataSet.circleRadius = 5f
    dataSet.setDrawCircleHole(false)
    dataSet.valueTextColor = ContextCompat.getColor(context, R.color.white)
    dataSet.valueTextSize = 10f
    dataSet.setDrawFilled(true)
    dataSet.fillDrawable = ContextCompat.getDrawable(context, R.drawable.shape_graph_faded)
  
    itemView.chart.setTouchEnabled(false)
    itemView.chart.axisRight.isEnabled = false
    itemView.chart.legend.isEnabled = false
    itemView.chart.description.isEnabled = false
    itemView.chart.setGridBackgroundColor(ContextCompat.getColor(context, R.color.white_80))
    itemView.chart.axisLeft.textColor = ContextCompat.getColor(context, R.color.white)
    itemView.chart.xAxis.textColor = ContextCompat.getColor(context, R.color.transparent)
    itemView.chart.xAxis.position = XAxis.XAxisPosition.BOTH_SIDED
    
    itemView.chart.data = LineData(dataSet)
    itemView.chart.invalidate()
    itemView.itemTitle.text = item.title
  }
}
