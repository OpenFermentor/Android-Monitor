package org.openfermentor.biomonitor.ui.experiment.view

import com.f2prateek.dart.InjectExtra
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.model.Experiment
import org.openfermentor.biomonitor.ui.common.base.BioMonitorSingleFragmentWithToolbarActivity

class ExperimentActivity : BioMonitorSingleFragmentWithToolbarActivity() {
  @InjectExtra
  lateinit var experiment: Experiment
  
  override val layoutResId = R.layout.activity_toolbar_no_shadow
  override val toolbarBackButtonDrawable = R.drawable.ic_arrow_back_white
  
  override fun createFragment() = ExperimentFragmentBuilder(experiment).build()
}
