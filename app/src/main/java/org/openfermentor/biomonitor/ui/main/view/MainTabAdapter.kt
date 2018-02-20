package org.openfermentor.biomonitor.ui.main.view

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import org.openfermentor.biomonitor.ui.main.tabs.experiments.ExperimentListFragmentBuilder
import org.openfermentor.biomonitor.ui.main.tabs.system.SystemStatusFragmentBuilder

class MainTabAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
  private val fragments = arrayOf(
      SystemStatusFragmentBuilder().build(),
      ExperimentListFragmentBuilder().build()
  )
  
  override fun getItem(position: Int) = fragments[position]
  
  override fun getCount() = fragments.size
  
  override fun getItemPosition(obj: Any?) = PagerAdapter.POSITION_NONE
}
