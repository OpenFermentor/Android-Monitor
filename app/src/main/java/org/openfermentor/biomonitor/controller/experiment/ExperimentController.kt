package org.openfermentor.biomonitor.controller.experiment

import com.xmartlabs.bigbang.core.controller.Controller
import org.openfermentor.biomonitor.service.ExperimentService
import javax.inject.Inject

class ExperimentController : Controller() {
  @Inject
  lateinit var experimentService: ExperimentService
  
  fun getExperiments(page: Int) = experimentService.getExperiments(page).applyIoSchedulers()
  
  fun searchExperiments(term: String) = experimentService.searchExperiments(term).applyIoSchedulers()
  
  fun getExperimentLog(experimentId: Int) = experimentService.getLog(experimentId).map { it.data }.applyIoSchedulers()
}
