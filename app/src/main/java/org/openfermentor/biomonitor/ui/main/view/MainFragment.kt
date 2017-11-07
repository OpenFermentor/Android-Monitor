package org.openfermentor.biomonitor.ui.main.view

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import kotlinx.android.synthetic.main.fragment_main.*
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.ui.common.base.BioMonitorFragment
import javax.inject.Inject

@FragmentWithArgs
class MainFragment : BioMonitorFragment<MainView, MainPresenter>(), MainView {
  @Inject
  override lateinit var presenter: MainPresenter
  
  override val layoutResId = R.layout.fragment_main
  
  override fun setup() {
    setupNavigationBar()
    setupViewPager()
  }
  
  private fun setupNavigationBar() {
    navigationBar.setOnNavigationItemSelectedListener {
      viewPager.setCurrentItem(if (it.itemId == R.id.system_status) 0 else 1, false)
      true
    }
  }
  
  private fun setupViewPager() {
    viewPager.adapter = MainTabAdapter(childFragmentManager)
  }
}
