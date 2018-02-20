package org.openfermentor.biomonitor.ui.main.tabs.experiments

import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.controller.experiment.ExperimentController
import org.openfermentor.biomonitor.helper.subscribers.GeneralSingleSubscriber
import org.openfermentor.biomonitor.model.Experiment
import org.openfermentor.biomonitor.service.response.ExperimentPageResponse
import org.openfermentor.biomonitor.ui.common.base.BioMonitorPresenter
import org.openfermentor.biomonitor.ui.common.base.prepareForSubscription
import javax.inject.Inject

class ExperimentListPresenter @Inject constructor() : BioMonitorPresenter<ExperimentListView>() {
  @Inject
  lateinit var experimentController: ExperimentController
  
  private var experiments = listOf<Experiment>()
  private var searching = false
  
  override fun attachView(view: ExperimentListView) {
    super.attachView(view)
    loadExperiments(view)
  }
  
  private fun loadExperiments(aView: ExperimentListView) = experimentController
      .getExperiments(1)
      .prepareForSubscription(aView)
      .subscribe(object : GeneralSingleSubscriber<ExperimentPageResponse>() {
        override fun onSuccess(t: ExperimentPageResponse) {
          experiments = t.experiments
          view?.experimentsLoaded(t.experiments)
        }
  
        override fun onError(throwable: Throwable) {
          super.onError(throwable)
  
          view?.showErrorMessage(App.context.getString(R.string.generic_error))
        }
      })
  
  fun experimentSearchClosed() {
    if (!searching) {
      view?.experimentsLoaded(experiments)
    }
  }
  
  fun experimentSearchOpened() {
    view?.clearSearchBar()
    view?.experimentsLoaded(emptyList())
  }
  
  fun search(term: String) {
    if (term.isBlank()) {
      view?.closeSearchBar()
    } else {
      searching = true
      view?.closeSearchBar()
      view?.setProgressBarVisible(true)
      view?.let { executeSearch(it, term) }
    }
  }
  
  private fun executeSearch(aView: ExperimentListView, term: String) = experimentController
      .searchExperiments(term)
      .prepareForSubscription(aView)
      .subscribe(object : GeneralSingleSubscriber<ExperimentPageResponse>() {
        override fun onSuccess(t: ExperimentPageResponse) {
          searching = false
          view?.experimentsLoaded(t.experiments)
        }
  
        override fun onError(throwable: Throwable) {
          super.onError(throwable)
          
          searching = false
          view?.showErrorMessage(App.context.getString(R.string.generic_error))
        }
      })
  
  fun onExperimentClicked(experiment: Experiment) = view?.goToExperimentActivity(experiment) ?: Unit
}
