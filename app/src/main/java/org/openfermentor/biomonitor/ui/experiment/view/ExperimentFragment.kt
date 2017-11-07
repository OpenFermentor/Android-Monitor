package org.openfermentor.biomonitor.ui.experiment.view

import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.hannesdorfmann.fragmentargs.bundler.ParcelerArgsBundler
import kotlinx.android.synthetic.main.fragment_experiment.*
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.Experiment
import org.openfermentor.biomonitor.ui.common.base.BioMonitorFragment
import javax.inject.Inject

@FragmentWithArgs
class ExperimentFragment : BioMonitorFragment<ExperimentView, ExperimentPresenter>(), ExperimentView {
  companion object {
    private const val VIEW_PAGER_CACHED_PAGES = 3
  }
  
  @Arg(bundler = ParcelerArgsBundler::class)
  lateinit var experiment: Experiment
  
  @Inject
  override lateinit var presenter: ExperimentPresenter
  
  override val layoutResId = R.layout.fragment_experiment
  
  override fun setup() {
    setTitle()
    viewPager.adapter = ExperimentTabAdapter(childFragmentManager, experiment)
    tabLayout.setupWithViewPager(viewPager)
    viewPager.offscreenPageLimit = VIEW_PAGER_CACHED_PAGES
  }
  
  private fun setTitle() {
    (activity as? ExperimentActivity)?.setToolbarTitle(experiment.title)
  }
}
