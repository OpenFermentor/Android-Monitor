package org.openfermentor.biomonitor.ui.experiment.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import org.openfermentor.biomonitor.model.Experiment
import org.openfermentor.biomonitor.ui.common.interfaces.Titleable
import org.openfermentor.biomonitor.ui.experiment.tabs.log.ExperimentLogFragmentBuilder
import org.openfermentor.biomonitor.ui.experiment.tabs.overview.ExperimentOverviewFragmentBuilder
import org.openfermentor.biomonitor.ui.experiment.tabs.report.ExperimentReportFragmentBuilder

class ExperimentTabAdapter(
    fragmentManager: FragmentManager,
    experiment: Experiment
) : FragmentPagerAdapter(fragmentManager) {
  private val fragments = listOf<Fragment>(
      ExperimentOverviewFragmentBuilder(experiment).build(),
      //ExperimentReportFragmentBuilder(experiment).build(),
      ExperimentLogFragmentBuilder(experiment).build()
  )
  
  override fun getItem(position: Int) = fragments[position]
  
  override fun getCount() = fragments.size
  
  override fun getItemPosition(obj: Any?) = PagerAdapter.POSITION_NONE
  
  override fun getPageTitle(position: Int) = (fragments[position] as? Titleable)?.title ?: ""
}
