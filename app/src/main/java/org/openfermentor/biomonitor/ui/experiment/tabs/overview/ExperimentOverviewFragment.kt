package org.openfermentor.biomonitor.ui.experiment.tabs.overview

import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.hannesdorfmann.fragmentargs.bundler.ParcelerArgsBundler
import kotlinx.android.synthetic.main.fragment_experiment_overview.*
import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.Experiment
import org.openfermentor.biomonitor.ui.common.base.BioMonitorFragment
import org.openfermentor.biomonitor.ui.common.interfaces.Titleable
import javax.inject.Inject

@FragmentWithArgs
class ExperimentOverviewFragment : BioMonitorFragment<ExperimentOverviewView, ExperimentOverviewPresenter>(),
    ExperimentOverviewView, Titleable {
  @Arg(bundler = ParcelerArgsBundler::class)
  lateinit var experiment: Experiment
  
  @Inject
  override lateinit var presenter: ExperimentOverviewPresenter
  
  override val layoutResId = R.layout.fragment_experiment_overview
  override val title = App.context.getString(R.string.overview)
  
  override fun setup() {
    basicInfo.setup(experiment)
    temperatureInfo.setup(experiment)
    phInfo.setup(experiment)
    pumpInfo.setup(experiment)
  }
}
